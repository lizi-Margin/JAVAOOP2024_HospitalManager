package src.Models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;

import src.Client;

public class Login extends JFrame {
    static final int LoginFailure = 0;
    static final int AccountVerifying = -1;
    static final int AdminAccount = 1;
    static final int DoctorAccount = 2;
    static final int PatientAccount = 3;

    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton registerButton;
    private final JLabel statusLabel;
    private final Choice userTypeChoice;

    public Login() {
        setTitle("Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        statusLabel = new JLabel("", SwingConstants.CENTER);

        Label userTypeLabel = new Label("User Type:");
        userTypeChoice = new Choice();
        userTypeChoice.add("Patient");
        userTypeChoice.add("Doctor");
        userTypeChoice.add("Admin");

        // Customize components
        usernameField.setPreferredSize(new Dimension(200, 30));
        passwordField.setPreferredSize(new Dimension(200, 30));
        loginButton.setPreferredSize(new Dimension(100, 30));
        registerButton.setPreferredSize(new Dimension(100, 30));
        userTypeLabel.setPreferredSize(new Dimension(100, 30));
        userTypeChoice.setPreferredSize(new Dimension(100, 30));
        statusLabel.setForeground(Color.RED);

        // Add components to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(loginButton, gbc);
        gbc.gridx = 1;
        mainPanel.add(registerButton, gbc);



        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(userTypeLabel,gbc);
        gbc.gridx = 1;
        mainPanel.add(userTypeChoice,gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        mainPanel.add(statusLabel, gbc);

        add(mainPanel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int loginResult = checkUserName(usernameField.getText(), new String(passwordField.getPassword()));

                } catch (Exception ex) {
                    ex.printStackTrace();
                    statusLabel.setText("Login failed due to an error.");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToRegisterModule();
                setVisible(false);
                dispose();
            }
        });
    }



    private int checkUserName(String userName, String password) throws Exception {
        if (!Client.dbManager.isConnected()) {
            this.handleLoginResult(LoginFailure,Client.nullUserId);
            return LoginFailure;
        }

        // Example SQL queries for different user types
        String adminSql = String.format("SELECT * FROM admin WHERE a_name = '%s' AND a_password = '%s'",userName,password);
        String doctorSql = String.format("SELECT * FROM doctor WHERE d_name = '%s' AND d_password = '%s'",userName,password);
        String patientSql = String.format("SELECT * FROM patient WHERE p_name = '%s' AND p_password = '%s'",userName,password);

        switch (userTypeChoice.getSelectedItem()) {
            case "Admin":
            // Admin check
            ResultSet rs = Client.dbManager.executeQuery(adminSql);
            if (rs.next()) {
                if (!rs.getBoolean("a_state")) {
                    this.handleLoginResult(AccountVerifying, Client.nullUserId);
                    return AccountVerifying;
                }
                System.out.println(rs.getBoolean("a_password_modified"));
                if (!rs.getBoolean("a_password_modified")) {
                    System.out.println("reset admin password");
                    PasswordDialog passwordDialog = new PasswordDialog(this);
                    passwordDialog.setVisible(true);
                    String newPass = passwordDialog.getPassword();
                    if (newPass.isEmpty()) {
                        this.handleLoginResult(LoginFailure, Client.nullUserId);
                        return LoginFailure;
                    }
                    adminSql = String.format("UPDATE admin SET a_password_modified = %b  , a_password = '%s'  WHERE a_id = %d", true, newPass, rs.getInt("a_id"));
                    System.out.println(adminSql);
                    Client.dbManager.executeUpdate(adminSql);
                }
                this.handleLoginResult(AdminAccount, rs.getInt("a_id"));
                return AdminAccount;
            }
            break;

            case "Doctor":
            // Doctor check
            rs = Client.dbManager.executeQuery(doctorSql);
            if (rs.next()) {
                if (!rs.getBoolean("d_state")) {
                    this.handleLoginResult(AccountVerifying, Client.nullUserId);
                    return AccountVerifying;
                }
                this.handleLoginResult(DoctorAccount, rs.getInt("d_id"));
                return DoctorAccount;
            }
            break;

            case "Patient":
            // Patient check
            rs = Client.dbManager.executeQuery(patientSql);
            if (rs.next()) {
                if (!rs.getBoolean("p_state")) {
                    this.handleLoginResult(AccountVerifying, Client.nullUserId);
                    return AccountVerifying;
                }
                this.handleLoginResult(PatientAccount, rs.getInt("p_id"));
                return PatientAccount;
            }
            break;

            default:
                break;
        }

        this.handleLoginResult(LoginFailure, Client.nullUserId);
        return LoginFailure;
    }

    private void handleLoginResult(int loginResult,int id) {
        switch (loginResult) {
            case AdminAccount:
                statusLabel.setText("Admin logged in.");
                Client.setUser(id,"Admin");
                switchToAdminModule();
                break;
            case DoctorAccount:
                statusLabel.setText("Doctor logged in.");
                Client.setUser(id,"Doctor");
                switchToDoctorModule();
                break;
            case PatientAccount:
                statusLabel.setText("Patient logged in.");
                Client.setUser(id,"Patient");
                switchToPatientModule();
                break;

            case AccountVerifying:
                statusLabel.setText("Account verying by admin. Please wait.");
                break;
            default:
                statusLabel.setText("Login failed.");
        }
    }

    // These methods should switch to the respective modules
    private void switchToAdminModule() {
        // Placeholder for switching to admin module
        JOptionPane.showMessageDialog(this, "Switching to Admin Module");
        Client.switchTo(Client.AdminModel);

        setVisible(false);
        dispose();
    }

    private void switchToDoctorModule() {
        // Placeholder for switching to doctor module
        JOptionPane.showMessageDialog(this, "Switching to Doctor Module");
        Client.switchTo(Client.DoctorModel);

        setVisible(false);
        dispose();
    }

    private void switchToPatientModule() {
        // Placeholder for switching to patient module
        JOptionPane.showMessageDialog(this, "Switching to Patient Module");
        Client.switchTo(Client.PatientModel);

        setVisible(false);
        dispose();
    }

    private void switchToRegisterModule() {
        switch (userTypeChoice.getSelectedItem()){
            case "Admin":
                Client.switchTo(Client.AdminRegisterModel);
                setVisible(false);
                dispose();
                break;
            case "Doctor":
                Client.switchTo(Client.DoctorRegisterModel);
                setVisible(false);
                dispose();
                break;
//            case "Patient":
            default:
                Client.switchTo(Client.PatientRegisterModel);
                setVisible(false);
                dispose();
                break;
        }
    }

    public class PasswordDialog extends JDialog {
        private JPasswordField passwordField;
        private String password;

        public PasswordDialog(Frame parent) {
            super(parent, "Password Reset", true);
            setSize(300, 150);
            setLayout(new BorderLayout());
            setLocationRelativeTo(parent);

            passwordField = new JPasswordField(20);
            JPanel panel = new JPanel();
            panel.add(new JLabel("Reset Password:"));
            panel.add(passwordField);

            JButton btnSubmit = new JButton("Submit");
            btnSubmit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    password = new String(passwordField.getPassword());
                    dispose();
                }
            });

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent windowEvent) {
                    password = "";
                    setVisible(false);
                    dispose();
                }
            });

            add(panel, BorderLayout.CENTER);
            add(btnSubmit, BorderLayout.SOUTH);
        }

        public String getPassword() {
            return password;
        }
    }
}



