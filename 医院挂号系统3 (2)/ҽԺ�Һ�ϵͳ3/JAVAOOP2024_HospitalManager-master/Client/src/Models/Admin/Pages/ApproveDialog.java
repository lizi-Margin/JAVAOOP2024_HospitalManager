package src.Models.Admin.Pages;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import src.Models.Admin.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

class ApproveDialog extends Dialog implements ActionListener {
    private Button approveButton, nextButton;
    private ResultSet resultSet;
    private String objectType;
    private int currentID;

    public ApproveDialog(Frame parent, ResultSet resultSet, String objectType) {
        super(parent, "Approve Object", true);
        this.resultSet = resultSet;
        this.objectType = objectType;

        setLayout(new BorderLayout());

        try {
            if (resultSet.next()) {
                currentID = resultSet.getInt(1);
                add(new Label("Object Info: " + resultSet.toString()), BorderLayout.CENTER);
            } else {
                add(new Label("No objects pending approval"), BorderLayout.CENTER);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        Panel panel = new Panel();
        approveButton = new Button("Approve");
        nextButton = new Button("Next");

        approveButton.addActionListener(this);
        nextButton.addActionListener(this);

        panel.add(approveButton);
        panel.add(nextButton);

        add(panel, BorderLayout.SOUTH);

        setSize(300, 150);
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
                Main.dbManager.executeUpdate("UPDATE information SET info_state = TRUE WHERE info_id = " + currentID);
            } else if (objectType.equals("Admin")) {
                Admin.approveAdmin(currentID);
            }

            // Show next object
            showNext();
        } else if (e.getSource() == nextButton) {
            // Show next object
            showNext();
        }
    }

    private void showNext() {
        try {
            if (resultSet.next()) {
                currentID = resultSet.getInt(1);
                remove(1);
                add(new Label("Object Info: " + resultSet.toString()), BorderLayout.CENTER);
                validate();
            } else {
                remove(1);
                add(new Label("No more objects pending approval"), BorderLayout.CENTER);
                validate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
