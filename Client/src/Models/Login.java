package src.Models;

import javax.security.auth.spi.LoginModule;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.Client;
import src.DatabaseManager.Patient;

public class Login extends JFrame {
    static final int LoginFailure = 0;
    static final int AdminAccount = 1;
    static final int DoctorAccount = 2;
    static final int PatientAccount = 3;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel statusLabel;

    public Login() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        statusLabel = new JLabel("", SwingConstants.CENTER);

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);
        panel.add(statusLabel);

        add(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int loginResult = checkUserName(usernameField.getText(), new String(passwordField.getPassword()));
                    handleLoginResult(loginResult);
                    setVisible(false);
                    dispose();
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
            return LoginFailure;
        }

        // Example SQL queries for different user types
        String adminSql = String.format("SELECT a_id FROM admin WHERE a_name = '%s' AND a_password = '%s'",userName,password);
        String doctorSql = String.format("SELECT d_id FROM doctor WHERE d_name = '%s' AND d_password = '%s'",userName,password);
        String patientSql = String.format("SELECT p_id FROM patient WHERE p_name = '%s' AND p_password = '%s'",userName,password);
        System.out.println(adminSql);

        // Admin check
        ResultSet rs = Client.dbManager.executeQuery(adminSql);
        if (rs.next()) {
            return AdminAccount;
        }

        // Doctor check
        rs = Client.dbManager.executeQuery(doctorSql);
        if (rs.next()) {
            return DoctorAccount;
        }

        // Patient check
        rs = Client.dbManager.executeQuery(patientSql);
        if (rs.next()) {
            return PatientAccount;
        }

        return LoginFailure;
    }

    private void handleLoginResult(int loginResult) {
        switch (loginResult) {
            case AdminAccount:
                statusLabel.setText("Admin logged in.");
                switchToAdminModule();
                break;
            case DoctorAccount:
                statusLabel.setText("Doctor logged in.");
                switchToDoctorModule();
                break;
            case PatientAccount:
                statusLabel.setText("Patient logged in.");
                switchToPatientModule();
                break;
            default:
                statusLabel.setText("Login failed.");
        }
    }

    // These methods should switch to the respective modules
    private void switchToAdminModule() {
        // Placeholder for switching to admin module
        JOptionPane.showMessageDialog(this, "Switching to Admin Module");
        Client.setCurrentModle = Client.AdminModel;

    }

    private void switchToDoctorModule() {
        // Placeholder for switching to doctor module
        JOptionPane.showMessageDialog(this, "Switching to Doctor Module");
        Client.setCurrentModle = Client.DoctorModel;
    }

    private void switchToPatientModule() {
        // Placeholder for switching to patient module
        JOptionPane.showMessageDialog(this, "Switching to Patient Module");
        Client.setCurrentModle = Client.PatientModel;
    }

    private void switchToRegisterModule() {
        Client.setCurrentModle = Client.RegisterModel;
    }

}



