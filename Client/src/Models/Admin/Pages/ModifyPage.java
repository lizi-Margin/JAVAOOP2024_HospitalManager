package src.Models.Admin.Pages;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.Models.Admin.*;


public class ModifyPage extends Frame implements ActionListener {
    private Choice objectTypeChoice;
    private Button modifyButton, backButton;

    public ModifyPage() {
        setLayout(new FlowLayout());

        objectTypeChoice = new Choice();
        objectTypeChoice.add("Patient");
        objectTypeChoice.add("Doctor");
        objectTypeChoice.add("Admin");

        modifyButton = new Button("Modify");
        backButton = new Button("Back");

        modifyButton.addActionListener(this);
        backButton.addActionListener(this);

        add(new Label("Select Object Type:"));
        add(objectTypeChoice);
        add(modifyButton);
        add(backButton);

        setTitle("Modify Operation");
        setSize(400, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == modifyButton) {
            String objectType = objectTypeChoice.getSelectedItem();

            if (objectType.equals("Patient")) {
                new ModifyPatientPage();
            } else if (objectType.equals("Doctor")) {
                new ModifyDoctorPage();
            } else if (objectType.equals("Admin")) {
                new ModifyAdminPage();
            }
        } else if (e.getSource() == backButton) {
            dispose();
        }
    }
}
