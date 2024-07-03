package src.Models.Admin.Pages;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.Models.Admin.Admin;

public class ModifyPatientPage extends Frame implements ActionListener {
    private TextField idField, nameField, genderField, ageField, phoneField, cardField, addressField, historyField;
    private Button modifyButton, backButton;

    public ModifyPatientPage() {
        setLayout(new GridLayout(9, 2));

        idField = new TextField();
        nameField = new TextField();
        genderField = new TextField();
        ageField = new TextField();
        phoneField = new TextField();
        cardField = new TextField();
        addressField = new TextField();
        historyField = new TextField();

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
        add(new Label("Age:"));
        add(ageField);
        add(new Label("Phone:"));
        add(phoneField);
        add(new Label("Card:"));
        add(cardField);
        add(new Label("Address:"));
        add(addressField);
        add(new Label("History:"));
        add(historyField);
        add(modifyButton);
        add(backButton);

        setTitle("Modify Patient Information");
        setSize(400, 300);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == modifyButton) {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String gender = genderField.getText();
            int age = Integer.parseInt(ageField.getText());
            String phone = phoneField.getText();
            String card = cardField.getText();
            String address = addressField.getText();
            String history = historyField.getText();

            Admin.updatePatientInfo(id, name, gender, age, phone, card, address, history);

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
