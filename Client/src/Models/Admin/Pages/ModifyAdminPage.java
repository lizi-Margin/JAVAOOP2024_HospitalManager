package src.Models.Admin.Pages;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.Models.Admin.*;

public class ModifyAdminPage extends Frame implements ActionListener {
    private TextField idField, nameField, genderField, cardField, phoneField, addressField;
    private Button modifyButton, backButton;

    public ModifyAdminPage() {
        setLayout(new GridLayout(7, 2));

        idField = new TextField();
        nameField = new TextField();
        genderField = new TextField();
        cardField = new TextField();
        phoneField = new TextField();
        addressField = new TextField();

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
        add(new Label("Card:"));
        add(cardField);
        add(new Label("Phone:"));
        add(phoneField);
        add(new Label("Address:"));
        add(addressField);
        add(modifyButton);
        add(backButton);

        setTitle("Modify Admin Information");
        setSize(400, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == modifyButton) {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String gender = genderField.getText();
            String card = cardField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();

            Admin.updateAdminInfo(id, name, gender, card, phone, address);

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
