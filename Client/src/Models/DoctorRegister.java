package src.Models;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.Client;

public class DoctorRegister extends Frame {
    private TextField idField;
    private TextField nameField;
    private TextField genderField;
    private TextField addressField;
    private TextField contactField;
    private TextField passwordField;

    private TextField postField;
    private TextField introductionField;
    private TextField sectionField;
    private TextField priceField;
    private TextField hospitalField;

    public  DoctorRegister() {
        setTitle("DoctorRegister");
        setSize(400, 300);
        setLayout(new GridLayout(13, 2));


        Label idLabel = new Label("Identity Card Index:");
        idField = new TextField();
        Label nameLabel = new Label("Name:");
        nameField = new TextField();
        Label genderLabel = new Label("Gender:");
        genderField = new TextField();
        Label addressLabel = new Label("Address:");
        addressField = new TextField();
        Label contactLabel = new Label("Phone Number:");
        contactField = new TextField();
        Label passwordLabel = new Label("Password:");
        passwordField = new TextField();

        Label postLabel = new Label("Post(Chief/Senior...):");
        postField = new TextField();;
        Label introLabel = new Label("Introduction:");
        introductionField = new TextField();
        Label sectionLabel = new Label("Section(Cardiology...):");
        sectionField = new TextField();
        Label priceLabel = new Label("Price:");
        priceField = new TextField();
        Label hospitalLabel = new Label("Hospital:");
        hospitalField = new TextField();

        Button registerButton = new Button("Register Now");
        registerButton.addActionListener(new RegisterButtonListener());

        add(idLabel);
        add(idField);
        add(nameLabel);
        add(nameField);
        add(postLabel);
        add(postField);
        add(introLabel);
        add(introductionField);
        add(sectionLabel);
        add(sectionField);
        add(hospitalLabel);
        add(hospitalField);
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

            String id = idField.getText();
            String name = nameField.getText();
            String gender = genderField.getText();
            String address = addressField.getText();
            String contact = contactField.getText();
            String password = passwordField.getText();

            String post = postField.getText();
            String intro = introductionField.getText();
            String section = sectionField.getText();
            String hospital = hospitalField.getText();

            System.out.println("身份证号: " + id);
            System.out.println("姓名: " + name);
            System.out.println("性别: " + gender);
            System.out.println("住址: " + address);
            System.out.println("联系方式: " + contact);


            try {
                String Sql = String.format("SELECT d_id FROM doctor WHERE d_name = '%s' AND d_password = '%s'", name, password);
                ResultSet rs = Client.dbManager.executeQuery(Sql);
                if (rs.next())
                    return;


                Sql = String.format("INSERT INTO doctor  ( d_password, d_name, d_gender, d_card, d_phone, d_address,d_post,d_introduction,d_section,d_hospital) VALUES ('%s' , '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                                    password,name,gender,id,contact,address,post,intro,section,hospital);

                Client.dbManager.executeUpdate(Sql);

            }catch (SQLException es){
                es.printStackTrace();
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

