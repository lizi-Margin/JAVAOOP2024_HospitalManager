package src.Models.Admin.Pages;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import src.Models.Admin.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ApprovePage extends Frame implements ActionListener {
    private Choice objectTypeChoice;
    private Button approveButton, backButton;

    public ApprovePage() {
        setLayout(new FlowLayout());

        objectTypeChoice = new Choice();
        objectTypeChoice.add("Patient");
        objectTypeChoice.add("Doctor");
        objectTypeChoice.add("Appointment Info");
        objectTypeChoice.add("Admin");

        approveButton = new Button("Approve");
        backButton = new Button("Back");

        approveButton.addActionListener(this);
        backButton.addActionListener(this);

        add(new Label("Select Object Type:"));
        add(objectTypeChoice);
        add(approveButton);
        add(backButton);

        setTitle("Approve Operation");
        setSize(400, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == approveButton) {
            String objectType = objectTypeChoice.getSelectedItem();

            // Handle approve operation
            ResultSet resultSet = null;
            if (objectType.equals("Patient")) {
                resultSet = Main.dbManager.executeQuery("SELECT * FROM patient WHERE p_state = FALSE");
            } else if (objectType.equals("Doctor")) {
                resultSet = Main.dbManager.executeQuery("SELECT * FROM doctor WHERE d_state = FALSE");
            } else if (objectType.equals("Appointment Info")) {
                resultSet = Main.dbManager.executeQuery("SELECT * FROM information WHERE info_state = FALSE");
            } else if (objectType.equals("Admin")) {
                resultSet = Main.dbManager.executeQuery("SELECT * FROM admin WHERE a_state = FALSE");
            }

            new ApproveDialog(this, resultSet, objectType);
        } else if (e.getSource() == backButton) {
            dispose();
        }
    }
}
