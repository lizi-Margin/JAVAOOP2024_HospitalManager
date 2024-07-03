package src.Models.Admin.Pages;

import src.Client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainPage extends Frame implements ActionListener {
    private Button deleteButton, modifyButton, approveButton, logoutButton, changePasswordButton, viewRecordsButton;
    int present_id = Client.userId; // 当前的管理员id;

    public AdminMainPage() {
        setLayout(new FlowLayout());

        deleteButton = new Button("Delete Operation");
        modifyButton = new Button("Modify Operation");
        approveButton = new Button("Approve Operation");
        changePasswordButton = new Button("Change Password");
        viewRecordsButton = new Button("View Records");
        logoutButton = new Button("Logout");

        deleteButton.addActionListener(this);
        modifyButton.addActionListener(this);
        approveButton.addActionListener(this);
        changePasswordButton.addActionListener(this);
        viewRecordsButton.addActionListener(this);
        logoutButton.addActionListener(this);

        add(deleteButton);
        add(modifyButton);
        add(approveButton);
        add(changePasswordButton);
        add(viewRecordsButton);
        add(logoutButton);

        setTitle("Admin Main Page");
        setSize(400, 300);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            new DeletePage();
        } else if (e.getSource() == modifyButton) {
            new ModifyPage();
        } else if (e.getSource() == approveButton) {
            new ApprovePage();
        } else if (e.getSource() == changePasswordButton) {
            new ChangePasswordPage(present_id);
        } else if (e.getSource() == viewRecordsButton) {
            new ViewRecordsPage();
        } else if (e.getSource() == logoutButton) {
            // Handle logout
            Client.switchTo(Client.EXIt);
            dispose();
        }
    }

    public static void main(String[] args) {
        new AdminMainPage();
    }
}
