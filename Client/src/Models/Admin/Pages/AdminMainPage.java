package src.Models.Admin.Pages;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminMainPage extends Frame implements ActionListener {
    private Button deleteButton, modifyButton, approveButton, logoutButton;

    public AdminMainPage() {
        setLayout(new FlowLayout());

        deleteButton = new Button("Delete Operation");
        modifyButton = new Button("Modify Operation");
        approveButton = new Button("Approve Operation");
        logoutButton = new Button("Logout");

        deleteButton.addActionListener(this);
        modifyButton.addActionListener(this);
        approveButton.addActionListener(this);
        logoutButton.addActionListener(this);

        add(deleteButton);
        add(modifyButton);
        add(approveButton);
        add(logoutButton);

        setTitle("Admin Main Page");
        setSize(400, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            new DeletePage();
        } else if (e.getSource() == modifyButton) {
            new ModifyPage();
        } else if (e.getSource() == approveButton) {
            new ApprovePage();
        } else if (e.getSource() == logoutButton) {
            // Handle logout
            dispose();
        }
    }

    public static void main(String[] args) {
        new AdminMainPage();
    }
}
