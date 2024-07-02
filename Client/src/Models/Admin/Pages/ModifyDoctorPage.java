package src.Models.Admin.Pages;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.Models.Admin.*;


public class ModifyDoctorPage extends Frame implements ActionListener {
    private TextField idField, nameField, genderField, phoneField, cardField, addressField, postField, introField, sectionField, priceField, hospitalField;
    private Button modifyButton, backButton;

    public ModifyDoctorPage() {
        setLayout(new GridLayout(12, 2));

        idField = new TextField();
        nameField = new TextField();
        genderField = new TextField();
        phoneField = new TextField();
        cardField = new TextField();
        addressField = new TextField();
        postField = new TextField();
        introField = new TextField();
        sectionField = new TextField();
        priceField = new TextField();
        hospitalField = new TextField();

        modifyButton = new Button("Modify");
        backButton = new Button("Back");

        modifyButton.addActionListener(this);
        backButton.addActionListener(this);

        add(new Label("ID:"));
        add(idField);
        add(new Label("Name:"));
        add(nameField);
        add(new Label("Gender:"));
        add(genderField);
        add(new Label("Phone:"));
        add(phoneField);
        add(new Label("Card:"));
        add(cardField);
        add(new Label("Address:"));
        add(addressField);
        add(new Label("Post:"));
        add(postField);
        add(new Label("Introduction:"));
        add(introField);
        add(new Label("Section:"));
        add(sectionField);
        add(new Label("Price:"));
        add(priceField);
        add(new Label("Hospital:"));
        add(hospitalField);
        add(modifyButton);
        add(backButton);

        setTitle("Modify Doctor Information");
        setSize(400, 400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == modifyButton) {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String gender = genderField.getText();
            String phone = phoneField.getText();
            String card = cardField.getText();
            String address = addressField.getText();
            String post = postField.getText();
            String intro = introField.getText();
            String section = sectionField.getText();
            double price = Double.parseDouble(priceField.getText());
            String hospital = hospitalField.getText();

            Admin.updateDoctorInfo(id, name, gender, phone, card, address, post, intro, section, price, hospital);

            // Show success dialog
            Dialog successDialog = new Dialog(this, "Success", true);
            successDialog.setLayout(new FlowLayout());
            successDialog.add(new Label("Modification successful"));
            Button okButton = new Button("OK");
            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    successDialog.setVisible(false);
                }
            });
            successDialog.add(okButton);
            successDialog.setSize(200, 100);

            successDialog.setVisible(true);
        } else if (e.getSource() == backButton) {
            dispose();
        }
    }
}
