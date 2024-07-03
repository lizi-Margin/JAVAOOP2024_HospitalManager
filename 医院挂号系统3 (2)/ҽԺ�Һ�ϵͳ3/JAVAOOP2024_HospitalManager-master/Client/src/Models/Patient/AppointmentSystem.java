package src.Models.Patient;

import src.Client;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.*;
import javax.swing.*;
import java.time.LocalDateTime;

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

        Button closeButton = new Button("Close");
        closeButton.addActionListener(e -> System.exit(0));
        add(closeButton);

        setSize(300, 200);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    private void loadChoices() {
        // Load dates from database
        try (Statement stmt = connection.createStatement()) {
            String dateQuery = "SELECT DISTINCT ar_time FROM arrange";
            ResultSet dateRs = stmt.executeQuery(dateQuery);
            while (dateRs.next()) {
                String ar_time = dateRs.getString("ar_time");
                dateChoice.add(ar_time);
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
        String initialDepartment = departmentChoice.getItem(0); // Assuming there's at least one department
        loadDoctorsForDepartment(initialDepartment);
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
            String doctorQuery = "SELECT d_id, d_price FROM doctor WHERE d_name = ?";
            PreparedStatement doctorStmt = connection.prepareStatement(doctorQuery);
            doctorStmt.setString(1, doctor);
            ResultSet doctorRs = doctorStmt.executeQuery();
            if (doctorRs.next()) {
                int doctorId = doctorRs.getInt("d_id");
                BigDecimal doctorPrice = doctorRs.getBigDecimal("d_price");

                String appointmentQuery = "INSERT INTO arrange (ar_time, d_id, p_id, info_id) VALUES (?, ?, ?, ?)";
                PreparedStatement appointmentStmt = connection.prepareStatement(appointmentQuery, Statement.RETURN_GENERATED_KEYS);
                appointmentStmt.setString(1, date);
                appointmentStmt.setInt(2, doctorId);
                appointmentStmt.setInt(3, getCurrentPatientId());
                appointmentStmt.setInt(4, 1); // Assuming info_id is 1 for this example
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

        TextArea textArea = new TextArea(10, 40);
        dialog.add(textArea);

        StringBuilder sb = new StringBuilder();
        sb.append("Appointments:").append("\n\n");

        String query = "SELECT ar_time, d_section, d_name FROM arrange JOIN doctor ON arrange.d_id = doctor.d_id";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String date = rs.getString("ar_time");
                String department = rs.getString("d_section");
                String doctor = rs.getString("d_name");

                sb.append("Date: ").append(date).append("\n")
                        .append("Department: ").append(department).append("\n")
                        .append("Doctor: ").append(doctor).append("\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        textArea.setText(sb.toString());

        Button closeButton = new Button("Close");
        closeButton.addActionListener(e -> dialog.dispose());
        dialog.add(closeButton);

        dialog.setSize(500, 300);
        dialog.setVisible(true);
    }

    private class PaymentDialog extends Dialog {
        private boolean paymentSuccessful;

        public PaymentDialog(Frame parent, String title, boolean modal, BigDecimal doctorPrice) {
            super(parent, title, modal);
            setLayout(new FlowLayout());

            Label amountLabel = new Label("Pay Amount: " + doctorPrice);
            add(amountLabel);

            Button payButton = new Button("Pay");
            payButton.addActionListener(e -> {
                // Logic to handle payment
                if (processPayment(doctorPrice)) {
                    paymentSuccessful = true;
                    JOptionPane.showMessageDialog(this, "Payment successful!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Payment failed!");
                }
            });
            add(payButton);

            Button closeButton = new Button("Close");
            closeButton.addActionListener(e -> dispose());
            add(closeButton);

            setSize(300, 150);
        }

        public boolean isPaymentSuccessful() {
            return paymentSuccessful;
        }

        private boolean processPayment(BigDecimal amount) {
            // Placeholder for actual payment processing logic
            // For example, you could integrate with a payment gateway API here
            return true; // Assuming payment is always successful for this example
        }
    }
}
