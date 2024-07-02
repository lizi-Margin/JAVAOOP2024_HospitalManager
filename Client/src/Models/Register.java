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

public class Register extends Frame {
    private TextField idField;
    private TextField nameField;
    private TextField ageField;
    private TextField genderField;
    private TextField addressField;
    private TextField contactField;
    private TextField passwordField;
    private Choice userTypeChoice;

    public  Register() {
        setTitle("Register");
        setSize(400, 300);
        setLayout(new GridLayout(9, 2));

        Label userTypeLabel = new Label("User Type:");
        userTypeChoice = new Choice();
        userTypeChoice.add("Patient");
        userTypeChoice.add("Doctor");
        userTypeChoice.add("Admin");

        Label idLabel = new Label("Identity Card Index:");
        idField = new TextField();
        Label nameLabel = new Label("Name:");
        nameField = new TextField();
        Label ageLabel = new Label("Age:");
        ageField = new TextField();
        Label genderLabel = new Label("Gender:");
        genderField = new TextField();
        Label addressLabel = new Label("Address:");
        addressField = new TextField();
        Label contactLabel = new Label("Phone Number:");
        contactField = new TextField();
        Label passwordLabel = new Label("Password:");
        passwordField = new TextField();

        Button registerButton = new Button("Register Now");
        registerButton.addActionListener(new RegisterButtonListener());

        add(userTypeLabel);
        add(userTypeChoice);
        add(idLabel);
        add(idField);
        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(genderLabel);
        add(genderField);
        add(addressLabel);
        add(addressField);
        add(contactLabel);
        add(contactField);
        add(passwordLabel);
        add(passwordField);
        add(new Label());
        add(registerButton);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                this.switchToLoginModule();
                setVisible(false);
                dispose();
            }
            private void switchToLoginModule(){
                Client.switchTo(Client.LoginModel);
                setVisible(false);
                dispose();
            }
        });
    }

    private class RegisterButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            boolean success = true;

            String userType = userTypeChoice.getSelectedItem();
            String id = idField.getText();
            String name = nameField.getText();
            int age = Integer.parseInt( ageField.getText());
            String gender = genderField.getText();
            String address = addressField.getText();
            String contact = contactField.getText();
            String password = passwordField.getText();


            System.out.println("用户类型: " + userType);
            System.out.println("身份证号: " + id);
            System.out.println("姓名: " + name);
            System.out.println("年龄: " + age);
            System.out.println("性别: " + gender);
            System.out.println("住址: " + address);
            System.out.println("联系方式: " + contact);

            if (!(userType.equals("Patient") ||userType.equals("Doctor") || userType.equals("Admin") ))
                return;
            if (!(userType.equals("Patient")  )) return;

            try {
                String Sql = switch (userType) {
                    case "Patient" ->
                            String.format("SELECT p_id FROM patient WHERE p_name = '%s' AND p_password = '%s'", name, password);
                    case "Doctor" ->
                            String.format("SELECT d_id FROM doctor WHERE d_name = '%s' AND d_password = '%s'", name, password);
                    default ->
                            String.format("SELECT a_id FROM admin WHERE a_name = '%s' AND a_password = '%s'", name, password);
                };
                ResultSet rs = Client.dbManager.executeQuery(Sql);
                if (rs.next())
                    return;


                Sql = switch (userType) {
                    case "Patient" ->
                            "SELECT COUNT(*) FROM patient;";
                    case "Doctor" ->
                            "SELECT COUNT(*) FROM doctor;";
                    default ->
                            "SELECT COUNT(*) FROM admin;";
                };
                rs = Client.dbManager.executeQuery(Sql);
                int len;
                if( rs.next())
                    len = rs.getInt(1);
                else
                    return;


                Sql = switch (userType) {
                    case "Patient" ->
                            String.format("INSERT INTO %s (p_id, p_password, p_name, p_gender, p_card, p_phone, p_address,p_age,p_history) VALUES (%d,'%s' , '%s', '%s', '%s', '%s', '%s',%d,'%s')",
                                    userType,len+1,password,name,gender,id,contact,address,(age),"None");
                    case "Doctor" ->
                            String.format("INSERT INTO %s (d_id, d_password, d_name, d_gender, d_card, d_phone, d_address) VALUES (%d,'%s' , '%s', '%s', '%s', '%s', '%s')",
                                    userType,len+1,password,name,gender,id,contact,address);
                    default ->
                            String.format("INSERT INTO %s (a_id, a_password, a_name, a_gender, a_card, a_phone, a_address) VALUES (%d,'%s' , '%s', '%s', '%s', '%s', '%s')",
                                    userType,len+1,password,name,gender,id,contact,address);
                };
                Client.dbManager.executeUpdate(Sql);

            }catch (SQLException es){
                es.printStackTrace();
                success = false;
            }

            switchToLoginModule();
        }
        private void switchToLoginModule(){
            Client.switchTo(Client.LoginModel);
            setVisible(false);
            dispose();
        }
    }
}

