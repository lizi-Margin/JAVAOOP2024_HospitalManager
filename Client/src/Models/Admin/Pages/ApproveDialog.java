package src.Models.Admin.Pages;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.Models.Admin.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import src.Models.Admin.Admin;
import src.Client;

public class ApproveDialog extends Dialog implements ActionListener {
    private final Button approveButton, nextButton, backButton;
    private ResultSet resultSet;
    private String objectType;
    private int currentID;
    private Label infoLabel;

    public ApproveDialog(Frame parent, ResultSet resultSet, String objectType) {
        super(parent, "Approve Object", true);
        this.resultSet = resultSet;
        this.objectType = objectType;

        setLayout(new BorderLayout());

        infoLabel = new Label();
        add(infoLabel, BorderLayout.CENTER);

        showNext();

        Panel panel = new Panel();
        approveButton = new Button("Approve");
        nextButton = new Button("Next");
        backButton = new Button("Back");

        approveButton.addActionListener(this);
        nextButton.addActionListener(this);
        backButton.addActionListener(this);

        panel.add(approveButton);
        panel.add(nextButton);
        panel.add(backButton);

        add(panel, BorderLayout.SOUTH);

        setSize(400, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == approveButton) {
            // Call approve method from Admin class
            if (objectType.equals("Patient")) {
                Admin.approvePatient(currentID);
            } else if (objectType.equals("Doctor")) {
                Admin.approveDoctor(currentID);
            } else if (objectType.equals("Appointment Info")) {
                Client.dbManager.executeUpdate("UPDATE information SET info_state = TRUE WHERE info_id = " + currentID);
            } else if (objectType.equals("Admin")) {
                Admin.approveAdmin(currentID);
            }

            // Show next object
            showNext();
        } else if (e.getSource() == nextButton) {
            // Show next object
            showNext();
        } else if (e.getSource() == backButton) {
            dispose(); // Close the current dialog
        }
    }

    private void showNext() {
        try {
            if (resultSet.next()) {
                if (objectType.equals("Patient")) {
                    currentID = resultSet.getInt("p_id");
                } else if (objectType.equals("Doctor")) {
                    currentID = resultSet.getInt("d_id");
                } else if (objectType.equals("Appointment Info")) {
                    currentID = resultSet.getInt("info_id");
                } else if (objectType.equals("Admin")) {
                    currentID = resultSet.getInt("a_id");
                }

                StringBuilder objectInfo = new StringBuilder();
                int columnCount = resultSet.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    objectInfo.append(resultSet.getMetaData().getColumnName(i))
                            .append(": ")
                            .append(resultSet.getString(i))
                            .append("\n");
                }
                infoLabel.setText(objectInfo.toString());
                validate();
            } else {
                infoLabel.setText("No more objects pending approval");
                approveButton.setEnabled(false);
                nextButton.setEnabled(false);
                validate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
