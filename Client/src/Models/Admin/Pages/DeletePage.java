package src.Models.Admin.Pages;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.Models.Admin.*;

public class DeletePage extends Frame implements ActionListener {
    private Choice objectTypeChoice;
    private TextField idField;
    private Button deleteButton, backButton;

    public DeletePage() {
        setLayout(new FlowLayout());

        objectTypeChoice = new Choice();
        objectTypeChoice.add("Patient");
        objectTypeChoice.add("Doctor");
        objectTypeChoice.add("Admin");

        idField = new TextField(10);

        deleteButton = new Button("Delete");
        backButton = new Button("Back");

        deleteButton.addActionListener(this);
        backButton.addActionListener(this);

        add(new Label("Select Object Type:"));
        add(objectTypeChoice);
        add(new Label("Enter ID:"));
        add(idField);
        add(deleteButton);
        add(backButton);

        setTitle("Delete Operation");
        setSize(400, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            String objectType = objectTypeChoice.getSelectedItem();
            int id = Integer.parseInt(idField.getText());

            // Handle delete operation
            String objectInfo = ""; // Get object info
            if (objectType.equals("Patient")) {
                // Fetch patient info from database
                // objectInfo = ...
            } else if (objectType.equals("Doctor")) {
                // Fetch doctor info from database
                // objectInfo = ...
            } else if (objectType.equals("Admin")) {
                // Fetch admin info from database
                // objectInfo = ...
            }

            // Show confirmation dialog
            new DeleteConfirmDialog(this, objectInfo, objectType, id);
        } else if (e.getSource() == backButton) {
            dispose();
        }
    }
}
