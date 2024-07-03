package src.Models.Admin.Pages;

import src.Models.Admin.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DeleteConfirmDialog extends Dialog implements ActionListener {
    private Button confirmButton, cancelButton;
    private int id;
    private String objectType;

    public DeleteConfirmDialog(Frame parent, String objectInfo, String objectType, int id) {
        super(parent, "Confirm Delete", true);
        this.id = id;
        this.objectType = objectType;

        setLayout(new BorderLayout());
        TextArea objectInfoArea = new TextArea(objectInfo);
        objectInfoArea.setEditable(false);
        add(objectInfoArea, BorderLayout.CENTER);

        Panel panel = new Panel();
        confirmButton = new Button("Confirm");
        cancelButton = new Button("Cancel");

        confirmButton.addActionListener(this);
        cancelButton.addActionListener(this);

        panel.add(confirmButton);
        panel.add(cancelButton);

        add(panel, BorderLayout.SOUTH);

        setSize(400, 300);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            // Call delete method from Admin class
            if (objectType.equals("Patient")) {
                Admin.deletePatient(id);
            } else if (objectType.equals("Doctor")) {
                Admin.deleteDoctor(id);
            } else if (objectType.equals("Admin")) {
                Admin.deleteAdmin(id);
            }
            dispose();
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }
}
