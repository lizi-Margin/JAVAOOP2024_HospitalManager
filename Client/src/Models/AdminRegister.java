package src.Models;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.Client;

public class AdminRegister extends Frame {
    private TextField idField;
    private TextField nameField;
    private TextField genderField;
    private TextField addressField;
    private TextField contactField;
    private TextField passwordField;

    public  AdminRegister() {
        setTitle("AdminRegister");
        setSize(400, 300);
        setLayout(new GridLayout(9, 2));


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

        Button registerButton = new Button("Register Now");
        registerButton.addActionListener(new RegisterButtonListener());

        add(idLabel);
        add(idField);
        add(nameLabel);
        add(nameField);
        add(genderLabel);
        add(genderField);
        add(addressLabel);
        add(addressField);
        add(contactLabel);
        add(contactField);
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


            System.out.println("身份证号: " + id);
            System.out.println("姓名: " + name);
            System.out.println("性别: " + gender);
            System.out.println("住址: " + address);
            System.out.println("联系方式: " + contact);


            try {
                String Sql = String.format("SELECT a_id FROM admin WHERE a_name = '%s' ", name);
                ResultSet rs = Client.dbManager.executeQuery(Sql);
                if (rs.next())
                    return;


                Sql = String.format("INSERT INTO admin  ( a_name, a_gender, a_card, a_phone, a_address) VALUES ( '%s', '%s', '%s', '%s', '%s')",
                        name,gender,id,contact,address);

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

