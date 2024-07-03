package src.Models.Patient;
import src.Client;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.*;
import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class AppointmentSystem {
    private Connection connection;

    public AppointmentSystem() {
        initConnection();

        EventQueue.invokeLater(() -> {
            new PatientDashboard(connection);
        });
    }

    private void initConnection() {
        String url = "jdbc:mysql://10.29.166.88:3306/javadb"; // Replace with your database URL
        String user = "root";
        String password = "password";  // Replace with your database password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connection established.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect to the database");
        }
    }

    public static void main(String[] args) {
        new AppointmentSystem();
    }
}

class PatientDashboard extends Frame implements ActionListener {
    private final Button bookAppointmentButton;
    private final Button viewAppointmentsButton;
    private final Button updateInfoButton;
    private final Button updatePasswordButton;
    private final Choice dateChoice;
    private final Choice departmentChoice;
    private final Choice doctorChoice;
    private final Connection connection;

    public PatientDashboard(Connection connection) {
        this.connection = connection;

        setLayout(new FlowLayout());
        setTitle("Patient Dashboard");

        dateChoice = new Choice();
        departmentChoice = new Choice();
        doctorChoice = new Choice();

        loadChoices();  // Load choices from database

        departmentChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedDepartment = departmentChoice.getSelectedItem();
                    loadDoctorsForDepartment(selectedDepartment);
                }
            }
        });

        bookAppointmentButton = new Button("Book Appointment");
        bookAppointmentButton.addActionListener(this);
        add(bookAppointmentButton);

        viewAppointmentsButton = new Button("View Appointments");
        viewAppointmentsButton.addActionListener(this);
        add(viewAppointmentsButton);

        updateInfoButton = new Button("Update Info");
        updateInfoButton.addActionListener(this);
        add(updateInfoButton);

        updatePasswordButton = new Button("Update Password");
        updatePasswordButton.addActionListener(this);
        add(updatePasswordButton);

        Button closeButton = new Button("Close");
        closeButton.addActionListener(e -> System.exit(0));
        add(closeButton);

        setSize(400, 300);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                Client.switchTo(Client.EXIt);
                dispose();
            }
        });
    }

    private void loadChoices() {
        // Clear previous items
        dateChoice.removeAll();

        // Load dates from database within three days from today
        LocalDate today = LocalDate.now();
        LocalDate threeDaysLater = today.plusDays(3);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDate = today.format(formatter);
        String endDate = threeDaysLater.format(formatter);

        try (PreparedStatement stmt = connection.prepareStatement("SELECT DISTINCT info_date FROM information WHERE info_date BETWEEN ? AND ?")) {
            stmt.setString(1, startDate);
            stmt.setString(2, endDate);
            ResultSet dateRs = stmt.executeQuery();
            while (dateRs.next()) {
                String infoDate = dateRs.getString("info_date");
                dateChoice.add(infoDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Load departments from database
        try (Statement stmt = connection.createStatement()) {
            String departmentQuery = "SELECT DISTINCT d_section FROM doctor";
            ResultSet departmentRs = stmt.executeQuery(departmentQuery);
            while (departmentRs.next()) {
                String section = departmentRs.getString("d_section");
                departmentChoice.add(section);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Load initial doctors based on the first department
        if (departmentChoice.getItemCount() > 0) {
            String initialDepartment = departmentChoice.getItem(0); // Assuming there's at least one department
            loadDoctorsForDepartment(initialDepartment);
        }
    }

    private void loadDoctorsForDepartment(String department) {
        doctorChoice.removeAll(); // Clear previous items

        try (PreparedStatement stmt = connection.prepareStatement("SELECT d_name FROM doctor WHERE d_section = ?")) {
            stmt.setString(1, department);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String doctorName = rs.getString("d_name");
                doctorChoice.add(doctorName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookAppointmentButton) {
            bookAppointment();
        } else if (e.getSource() == viewAppointmentsButton) {
            viewAppointments();
        } else if (e.getSource() == updateInfoButton) {
            new UpdatePatientInfoDialog(this, "Update Info", true, connection, getCurrentPatientId()).setVisible(true);
        } else if (e.getSource() == updatePasswordButton) {
            new UpdatePatientPasswordDialog(this, "Update Password", true, connection, getCurrentPatientId()).setVisible(true);
        }
    }

    private void bookAppointment() {
        Dialog dialog = new Dialog(this, "Book Appointment", true);
        dialog.setLayout(new FlowLayout());

        Label dateLabel = new Label("Select Date:");
        dialog.add(dateLabel);
        dialog.add(dateChoice);

        Label departmentLabel = new Label("Select Department:");
        dialog.add(departmentLabel);
        dialog.add(departmentChoice);

        Label doctorLabel = new Label("Select Doctor:");
        dialog.add(doctorLabel);
        dialog.add(doctorChoice);

        Button bookButton = new Button("Book");
        bookButton.addActionListener(e -> {
            String date = dateChoice.getSelectedItem();
            String department = departmentChoice.getSelectedItem();
            String doctor = doctorChoice.getSelectedItem();

            // Save appointment to the database
            saveAppointment(date, department, doctor);

            dialog.dispose();
        });
        dialog.add(bookButton);

        Button closeButton = new Button("Close");
        closeButton.addActionListener(e -> dialog.dispose());
        dialog.add(closeButton);

        dialog.setSize(300, 200);
        dialog.setVisible(true);
    }

    private void saveAppointment(String date, String department, String doctor) {
        try {
            String doctorQuery = "SELECT i.d_id, i.info_id, i.info_price FROM information i " +
                    "INNER JOIN doctor d ON i.d_id = d.d_id " +
                    "WHERE i.info_date = ? AND d.d_name = ?";
            PreparedStatement doctorStmt = connection.prepareStatement(doctorQuery);
            doctorStmt.setString(1, date);
            doctorStmt.setString(2, doctor);
            ResultSet doctorRs = doctorStmt.executeQuery();

            if (doctorRs.next()) {
                int doctorId = doctorRs.getInt("d_id");
                int infoId = doctorRs.getInt("info_id");
                BigDecimal doctorPrice = doctorRs.getBigDecimal("info_price");

                String appointmentQuery = "INSERT INTO arrange (ar_time, d_id, p_id, info_id) VALUES (?, ?, ?, ?)";
                PreparedStatement appointmentStmt = connection.prepareStatement(appointmentQuery, Statement.RETURN_GENERATED_KEYS);
                appointmentStmt.setString(1, date);
                appointmentStmt.setInt(2, doctorId);
                appointmentStmt.setInt(3, getCurrentPatientId());
                appointmentStmt.setInt(4, infoId);
                appointmentStmt.executeUpdate();

                ResultSet generatedKeys = appointmentStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int arrangeId = generatedKeys.getInt(1);
                    saveOrder(arrangeId, doctorId, doctorPrice);
                }

                PaymentDialog paymentDialog = new PaymentDialog(this, "Payment", true, doctorPrice);
                paymentDialog.setVisible(true);

                if (paymentDialog.isPaymentSuccessful()) {
                    JOptionPane.showMessageDialog(this, "Appointment booked successfully:\n" +
                            "Date: " + date + "\nDepartment: " + department + "\nDoctor: " + doctor);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No doctor found for the selected date and name.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
    }


    private void saveOrder(int arrangeId, int doctorId, BigDecimal doctorPrice) {
        String query = "INSERT INTO orders (p_id, d_id, o_history, o_start, o_end, o_state, o_total_price, o_price_state) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, getCurrentPatientId());
            stmt.setInt(2, doctorId);
            stmt.setString(3, "No history");
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now().plusMinutes(30)));
            stmt.setString(6, "Pending");
            stmt.setBigDecimal(7, doctorPrice);
            stmt.setBoolean(8, false);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getCurrentPatientId() {
        if (Client.hasUser() && Client.userType.equals("Patient"))
            return Client.userId;
        else
            return -1;
    }

    private void viewAppointments() {
        Dialog dialog = new Dialog(this, "View Appointments", true);
        dialog.setLayout(new FlowLayout());

        Label startDateLabel = new Label("Start Date:");
        Choice startDateChoice = new Choice();
        Label endDateLabel = new Label("End Date:");
        Choice endDateChoice = new Choice();

        LocalDate today = LocalDate.now();
        LocalDate threeDaysLater = today.plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Add past dates in reverse order
        for (int i = 30; i >= 0; i--) {
            LocalDate pastDate = today.minusDays(i);
            String pastDateString = pastDate.format(formatter);
            startDateChoice.add(pastDateString);
            endDateChoice.add(pastDateString);
        }

        // Add today and future dates in forward order
        for (int i = 0; i <= 3; i++) {
            LocalDate futureDate = today.plusDays(i);
            String futureDateString = futureDate.format(formatter);
            startDateChoice.add(futureDateString);
            endDateChoice.add(futureDateString);
        }

        dialog.add(startDateLabel);
        dialog.add(startDateChoice);
        dialog.add(endDateLabel);
        dialog.add(endDateChoice);

        TextArea textArea = new TextArea(10, 40);
        dialog.add(textArea);

        Button filterButton = new Button("Filter");
        filterButton.addActionListener(e -> {
            String startDate = startDateChoice.getSelectedItem();
            String endDate = endDateChoice.getSelectedItem();
            displayAppointments(textArea, startDate, endDate);
        });
        dialog.add(filterButton);

        // Display all historical and future appointments for the current user initially
        displayAppointments(textArea, null, null);

        Button closeButton = new Button("Close");
        closeButton.addActionListener(e -> dialog.dispose());
        dialog.add(closeButton);

        dialog.setSize(500, 300);
        dialog.setVisible(true);
    }

    private void displayAppointments(TextArea textArea, String startDate, String endDate) {
        StringBuilder sb = new StringBuilder();
        sb.append("Appointments:").append("\n\n");

        String query = "SELECT o.o_start, o.o_end, d.d_section, d.d_name, o.o_total_price, o.o_state " +
                "FROM orders o " +
                "JOIN doctor d ON o.d_id = d.d_id " +
                "WHERE o.p_id = ?";

        if (startDate != null && endDate != null) {
            query += " AND o.o_start BETWEEN ? AND ?";
        }

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, getCurrentPatientId());

            if (startDate != null && endDate != null) {
                stmt.setString(2, startDate + " 00:00:00");
                stmt.setString(3, endDate + " 23:59:59");
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String start = rs.getString("o_start");
                String end = rs.getString("o_end");
                String department = rs.getString("d_section");
                String doctor = rs.getString("d_name");
                BigDecimal price = rs.getBigDecimal("o_total_price");
                String state = rs.getString("o_state");

                sb.append("Start: ").append(start).append(", End: ").append(end)
                        .append(", Department: ").append(department).append(", Doctor: ").append(doctor)
                        .append(", Price: ").append(price).append(", State: ").append(state).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        textArea.setText(sb.toString());
    }


    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");
            new PatientDashboard(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class UpdatePatientInfoDialog extends Dialog {
    private final TextField nameField;
    private final TextField genderField;
    private final TextField cardField;
    private final TextField phoneField;
    private final TextField addressField;
    private final TextField ageField;
    private final TextField historyField;

    public UpdatePatientInfoDialog(Frame parent, String title, boolean modal, Connection connection, int patientId) {
        super(parent, title, modal);
        setLayout(new GridLayout(8, 2));

        Label nameLabel = new Label("Name:");
        nameField = new TextField();
        add(nameLabel);
        add(nameField);

        Label genderLabel = new Label("Gender:");
        genderField = new TextField();
        add(genderLabel);
        add(genderField);

        Label cardLabel = new Label("Card:");
        cardField = new TextField();
        add(cardLabel);
        add(cardField);

        Label phoneLabel = new Label("Phone:");
        phoneField = new TextField();
        add(phoneLabel);
        add(phoneField);

        Label addressLabel = new Label("Address:");
        addressField = new TextField();
        add(addressLabel);
        add(addressField);

        Label ageLabel = new Label("Age:");
        ageField = new TextField();
        add(ageLabel);
        add(ageField);

        Label historyLabel = new Label("History:");
        historyField = new TextField();
        add(historyLabel);
        add(historyField);

        Button updateButton = new Button("Update");
        updateButton.addActionListener(e -> {
            updatePatientInfo(connection, patientId);
        });
        add(updateButton);

        Button closeButton = new Button("Close");
        closeButton.addActionListener(e -> dispose());
        add(closeButton);

        loadPatientInfo(connection, patientId);

        setSize(300, 400);
    }

    private void loadPatientInfo(Connection connection, int patientId) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT p_name, p_gender, p_card, p_phone, p_address, p_age, p_history FROM patient WHERE p_id = ?")) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nameField.setText(rs.getString("p_name"));
                genderField.setText(rs.getString("p_gender"));
                cardField.setText(rs.getString("p_card"));
                phoneField.setText(rs.getString("p_phone"));
                addressField.setText(rs.getString("p_address"));
                ageField.setText(rs.getString("p_age"));
                historyField.setText(rs.getString("p_history"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updatePatientInfo(Connection connection, int patientId) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE patient SET p_name = ?, p_gender = ?, p_card = ?, p_phone = ?, p_address = ?, p_age = ?, p_history = ? WHERE p_id = ?")) {
            stmt.setString(1, nameField.getText());
            stmt.setString(2, genderField.getText());
            stmt.setString(3, cardField.getText());
            stmt.setString(4, phoneField.getText());
            stmt.setString(5, addressField.getText());
            stmt.setString(6, ageField.getText());
            stmt.setString(7, historyField.getText());
            stmt.setInt(8, patientId);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Patient info updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to update patient info!");
        }
    }
}

class UpdatePatientPasswordDialog extends Dialog {
    private final JPasswordField currentPasswordField;
    private final JPasswordField newPasswordField;
    private final JPasswordField confirmPasswordField;

    public UpdatePatientPasswordDialog(Frame parent, String title, boolean modal, Connection connection, int patientId) {
        super(parent, title, modal);
        setLayout(new GridLayout(4, 2));

        Label currentPasswordLabel = new Label("Current Password:");
        currentPasswordField = new JPasswordField();
        add(currentPasswordLabel);
        add(currentPasswordField);

        Label newPasswordLabel = new Label("New Password:");
        newPasswordField = new JPasswordField();
        add(newPasswordLabel);
        add(newPasswordField);

        Label confirmPasswordLabel = new Label("Confirm Password:");
        confirmPasswordField = new JPasswordField();
        add(confirmPasswordLabel);
        add(confirmPasswordField);

        Button updateButton = new Button("Update");
        updateButton.addActionListener(e -> {
            updatePatientPassword(connection, patientId);
        });
        add(updateButton);

        Button closeButton = new Button("Close");
        closeButton.addActionListener(e -> dispose());
        add(closeButton);

        setSize(300, 200);
    }

    private void updatePatientPassword(Connection connection, int patientId) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT p_password FROM patient WHERE p_id = ?")) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String currentPassword = new String(currentPasswordField.getPassword());
                String storedPassword = rs.getString("p_password");

                if (!currentPassword.equals(storedPassword)) {
                    JOptionPane.showMessageDialog(this, "Current password is incorrect!");
                    return;
                }

                String newPassword = new String(newPasswordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (!newPassword.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(this, "New password and confirm password do not match!");
                    return;
                }

                String updateQuery = "UPDATE patient SET p_password = ? WHERE p_id = ?";
                try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                    updateStmt.setString(1, newPassword);
                    updateStmt.setInt(2, patientId);
                    updateStmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Password updated successfully!");
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Failed to update password!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class PaymentDialog extends Dialog {
    private final BigDecimal amount;
    private boolean paymentSuccessful;

    public PaymentDialog(Frame parent, String title, boolean modal, BigDecimal amount) {
        super(parent, title, modal);
        this.amount = amount;
        this.paymentSuccessful = false;

        setLayout(new FlowLayout());

        Label amountLabel = new Label("Amount to pay: " + amount.toString());
        add(amountLabel);

        Button payButton = new Button("Pay");
        payButton.addActionListener(e -> {
            paymentSuccessful = true;
            dispose();
        });
        add(payButton);

        Button cancelButton = new Button("Cancel");
        cancelButton.addActionListener(e -> dispose());
        add(cancelButton);

        setSize(300, 150);
    }

    public boolean isPaymentSuccessful() {
        return paymentSuccessful;
    }
}
