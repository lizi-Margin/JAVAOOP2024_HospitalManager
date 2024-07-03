package src.Models.Admin.Pages;

import src.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ViewRecordsPage extends Frame implements ActionListener {
    private Button backButton;
    private TextArea recordsTextArea;

    public ViewRecordsPage() {
        setLayout(new BorderLayout());

        recordsTextArea = new TextArea();
        recordsTextArea.setEditable(false);
        add(recordsTextArea, BorderLayout.CENTER);

        backButton = new Button("Back");
        backButton.addActionListener(this);
        add(backButton, BorderLayout.SOUTH);

        setTitle("View Records");
        setSize(600, 400);
        setVisible(true);
        setLocationRelativeTo(null);

        displayRecords();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            dispose(); // Close the current window
        }
    }

    private void displayRecords() {
        String query = "SELECT * FROM record";
        ResultSet resultSet = Client.dbManager.executeQuery(query);
        StringBuilder records = new StringBuilder();

        try {
            while (resultSet.next()) {
                int recordId = resultSet.getInt("record_id");
                Timestamp recordTime = resultSet.getTimestamp("record_time");
                int recordObject = resultSet.getInt("record_object");
                int recordType = resultSet.getInt("record_type");

                records.append("Record ID: ").append(recordId)
                        .append(", Time: ").append(recordTime)
                        .append(", Object: ").append(recordObject)
                        .append(", Type: ").append(recordType)
                        .append("\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        recordsTextArea.setText(records.toString());
    }
}
