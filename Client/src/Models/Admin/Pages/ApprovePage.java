package src.Models.Admin.Pages;

import java.awt.*;
import src.Client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import src.Models.Admin.*;
import src.DatabaseManager.DatabaseManager;

public class ApprovePage extends Frame implements ActionListener {
    private Button patientButton, doctorButton, infoButton, adminButton, backButton;
    Dialog tmp;

    public ApprovePage() {
        setLayout(new GridLayout(5, 1));

        patientButton = new Button("Approve Patients");
        doctorButton = new Button("Approve Doctors");
        infoButton = new Button("Approve Appointment Info");
        adminButton = new Button("Approve Admins");
        backButton = new Button("Back");

        patientButton.addActionListener(this);
        doctorButton.addActionListener(this);
        infoButton.addActionListener(this);
        adminButton.addActionListener(this);
        backButton.addActionListener(this);

        add(patientButton);
        add(doctorButton);
        add(infoButton);
        add(adminButton);
        add(backButton);

        setTitle("Approve Page");
        setSize(400, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == patientButton) {
            showApproveDialog("Patient");
        } else if (e.getSource() == doctorButton) {
            showApproveDialog("Doctor");
        } else if (e.getSource() == infoButton) {
            showApproveDialog("Appointment Info");
        } else if (e.getSource() == adminButton) {
            showApproveDialog("Admin");
        } else if (e.getSource() == backButton) {
            dispose();
        }
    }

    private void showApproveDialog(String objectType) {
        ResultSet resultSet = null;
        String query = null;
        if (objectType.equals("Patient")) {
            query = "SELECT * FROM patient WHERE p_state = FALSE";
        } else if (objectType.equals("Doctor")) {
            query = "SELECT * FROM doctor WHERE d_state = FALSE";
        } else if (objectType.equals("Appointment Info")) {
            query = "SELECT * FROM information WHERE info_state = FALSE";
        } else if (objectType.equals("Admin")) {
            query = "SELECT * FROM admin WHERE a_state = FALSE";
        }

        if (query != null) {
            resultSet = Client.dbManager.executeQuery(query);
        }

        try {
            if (resultSet != null && resultSet.isBeforeFirst()) { // Check if the result set is not empty
                tmp = new ApproveDialog(this, resultSet, objectType);
            } else {
                showNoPendingApprovalsDialog();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            showNoPendingApprovalsDialog();
        }
    }

    private void showNoPendingApprovalsDialog() {
        Dialog errorDialog = new Dialog(this, "No Pending Approvals", true);
        errorDialog.setLayout(new FlowLayout());
        errorDialog.add(new Label("No pending approvals or error in fetching data."));
        Button okButton = new Button("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                errorDialog.setVisible(false);
                errorDialog.dispose();
            }
        });
        errorDialog.add(okButton);
        errorDialog.setSize(300, 150);
        errorDialog.setVisible(true);
    }
}
