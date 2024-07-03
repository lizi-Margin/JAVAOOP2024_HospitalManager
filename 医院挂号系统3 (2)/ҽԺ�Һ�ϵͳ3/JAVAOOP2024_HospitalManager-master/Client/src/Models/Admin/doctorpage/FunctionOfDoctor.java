package src.Models.Admin.doctorpage;

import src.Client;

import java.awt.*;
import java.awt.event.*;

public class FunctionOfDoctor extends Frame implements ActionListener {
    private Button addInfoButton, deleteInfoButton, doctorArrangementButton, doctorDetailsButton, updatePasswordButton;

    public FunctionOfDoctor() {
        setTitle("功能选择窗口");
        setSize(400, 300);
        setLayout(new GridLayout(5, 1));

        addInfoButton = new Button("Add Information");
        deleteInfoButton = new Button("Delete Information");
        doctorArrangementButton = new Button("Doctor Arrangement");
        doctorDetailsButton = new Button("Doctor Details");
        updatePasswordButton = new Button("Doctor Update Password");

        addInfoButton.addActionListener(this);
        deleteInfoButton.addActionListener(this);
        doctorArrangementButton.addActionListener(this);
        doctorDetailsButton.addActionListener(this);
        updatePasswordButton.addActionListener(this);

        add(addInfoButton);
        add(deleteInfoButton);
        add(doctorArrangementButton);
        add(doctorDetailsButton);
        add(updatePasswordButton);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addInfoButton) {
            new AddInformation();
        } else if (e.getSource() == deleteInfoButton) {
            new DeleteInformation();
        } else if (e.getSource() == doctorArrangementButton) {
            new DoctorArrangement();
        } else if (e.getSource() == doctorDetailsButton) {
            new DoctorDetailsGUI();
        } else if (e.getSource() == updatePasswordButton) {
            new DoctorUpdatePassword();
        }
    }

    public static void main(String[] args) {
        new FunctionOfDoctor();
        int id = Client.userId;
    }
}
