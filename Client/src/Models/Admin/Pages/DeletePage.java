package src.Models.Admin.Pages;

import src.Client;
import src.Models.Admin.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            String objectType = objectTypeChoice.getSelectedItem();
            int id = Integer.parseInt(idField.getText());
            String objectInfo = fetchObjectInfo(objectType, id);

            if (!objectInfo.isEmpty()) {
                new DeleteConfirmDialog(this, objectInfo, objectType, id);
            } else {
                showMessage("Object not found or error in fetching data.");
            }
        } else if (e.getSource() == backButton) {
            dispose();
        }
    }

    private String fetchObjectInfo(String objectType, int id) {
        String query = null;
        if (objectType.equals("Patient")) {
            query = "SELECT * FROM patient WHERE p_id = " + id;
        } else if (objectType.equals("Doctor")) {
            query = "SELECT * FROM doctor WHERE d_id = " + id;
        } else if (objectType.equals("Admin")) {
            query = "SELECT * FROM admin WHERE a_id = " + id;
        }

        if (query != null) {
            ResultSet resultSet = Client.dbManager.executeQuery(query);
            try {
                if (resultSet.next()) {
                    StringBuilder objectInfo = new StringBuilder();
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        objectInfo.append(resultSet.getMetaData().getColumnName(i))
                                .append(": ")
                                .append(resultSet.getString(i))
                                .append("\n");
                    }
                    return objectInfo.toString();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return "";
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
    }
}
