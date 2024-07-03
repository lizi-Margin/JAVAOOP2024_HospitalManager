package src.Models.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserManagement extends Frame implements ActionListener {
    TextField tfId, tfName, tfAge, tfGender, tfAddress, tfContact, tfPassword;
    Choice userTypeChoice;
    TextArea outputArea;
    Button addBtn, updateBtn, deleteBtn;

    public UserManagement() {
        setLayout(new FlowLayout());

        add(new Label("ID:"));
        tfId = new TextField(20);
        add(tfId);

        add(new Label("Name:"));
        tfName = new TextField(20);
        add(tfName);

        add(new Label("Age:"));
        tfAge = new TextField(20);
        add(tfAge);

        add(new Label("Gender:"));
        tfGender = new TextField(20);
        add(tfGender);

        add(new Label("Address:"));
        tfAddress = new TextField(20);
        add(tfAddress);

        add(new Label("Contact:"));
        tfContact = new TextField(20);
        add(tfContact);

        add(new Label("Password:"));
        tfPassword = new TextField(20);
        add(tfPassword);

        add(new Label("User Type:"));
        userTypeChoice = new Choice();
        userTypeChoice.add("Patient");
        userTypeChoice.add("Doctor");
        userTypeChoice.add("Admin");
        add(userTypeChoice);

        addBtn = new Button("Add User");
        addBtn.addActionListener(this);
        add(addBtn);

        updateBtn = new Button("Update User");
        updateBtn.addActionListener(this);
        add(updateBtn);

        deleteBtn = new Button("Delete User");
        deleteBtn.addActionListener(this);
        add(deleteBtn);

        outputArea = new TextArea(10, 50);
        add(outputArea);

        setTitle("User Management");
        setSize(600, 400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            // Code to add user to the database
        } else if (e.getSource() == updateBtn) {
            // Code to update user information in the database
        } else if (e.getSource() == deleteBtn) {
            // Code to delete user from the database
        }
    }

    public static void main(String[] args) {
        new UserManagement();
    }
}
