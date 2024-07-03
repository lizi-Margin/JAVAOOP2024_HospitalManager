package src.Models.Admin.Pages;

import src.Client;
import src.Models.Admin.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangePasswordPage extends Frame implements ActionListener {
    private TextField oldPasswordField, newPasswordField;
    private Button changeButton, backButton;
    private int adminId;

    public ChangePasswordPage(int adminId) {
        this.adminId = adminId;

        setLayout(new GridLayout(3, 2));

        oldPasswordField = new TextField();
        newPasswordField = new TextField();
        oldPasswordField.setEchoChar('*');
        newPasswordField.setEchoChar('*');

        changeButton = new Button("Change Password");
        backButton = new Button("Back");

        changeButton.addActionListener(this);
        backButton.addActionListener(this);

        add(new Label("Old Password:"));
        add(oldPasswordField);
        add(new Label("New Password:"));
        add(newPasswordField);
        add(changeButton);
        add(backButton);

        setTitle("Change Password");
        setSize(400, 200);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changeButton) {
            String oldPassword = oldPasswordField.getText();
            String newPassword = newPasswordField.getText();
            if (isValidOldPassword(adminId, oldPassword)) {
                Admin.updatePassword(adminId, newPassword);
                showMessage("Password changed successfully!");
            } else {
                showMessage("Incorrect old password!");
            }
        } else if (e.getSource() == backButton) {
            dispose(); // Close the current window
        }
    }

    private boolean isValidOldPassword(int adminId, String oldPassword) {
        // Validate the old password (this function needs to be implemented)
        String query = "SELECT a_password FROM admin WHERE a_id = " + adminId;
        ResultSet resultSet = Client.dbManager.executeQuery(query);
        try {
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("a_password");
                return storedPassword.equals(oldPassword);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private void showMessage(String message) {
        Dialog messageDialog = new Dialog(this, "Message", true);
        messageDialog.setLayout(new FlowLayout());
        messageDialog.add(new Label(message));
        Button okButton = new Button("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                messageDialog.setVisible(false);
                messageDialog.dispose();
            }
        });
        messageDialog.add(okButton);
        messageDialog.setSize(300, 150);
        messageDialog.setVisible(true);
        setLocationRelativeTo(null);
    }
}
