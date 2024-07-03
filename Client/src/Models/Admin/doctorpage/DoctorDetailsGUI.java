package src.Models.Admin.doctorpage;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import src.Client;
import src.Models.Admin.Doctor;

public class DoctorDetailsGUI extends Frame {
    private TextField tfName, tfGender, tfPhone, tfCard, tfAddress, tfPost, tfIntroduction, tfSection, tfPrice, tfHospital;
    private Button btnSave;
    private Doctor currentDoctor;

    public DoctorDetailsGUI() {
        setTitle("Doctor Details");
        setSize(400, 400);
        setLayout(new GridLayout(12, 2));

        // 初始化界面元素
        tfName = new TextField(20);
        tfGender = new TextField(10);
        tfPhone = new TextField(15);
        tfCard = new TextField(15);
        tfAddress = new TextField(30);
        tfPost = new TextField(20);
        tfIntroduction = new TextField(30);
        tfSection = new TextField(20);
        tfPrice = new TextField(10);
        tfHospital = new TextField(20);

        // 添加标签和文本框到窗口
        add(new Label("Name:"));
        add(tfName);
        add(new Label("Gender:"));
        add(tfGender);
        add(new Label("Phone:"));
        add(tfPhone);
        add(new Label("Card:"));
        add(tfCard);
        add(new Label("Address:"));
        add(tfAddress);
        add(new Label("Post:"));
        add(tfPost);
        add(new Label("Introduction:"));
        add(tfIntroduction);
        add(new Label("Section:"));
        add(tfSection);
        add(new Label("Price:"));
        add(tfPrice);
        add(new Label("Hospital:"));
        add(tfHospital);

        btnSave = new Button("Save Changes");
        add(btnSave);

        // 事件处理：保存按钮点击
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });

        // 加载当前登录医生的详细信息
        loadDoctorDetails(Client.userId);

        // 窗口关闭事件处理
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    // 加载医生详细信息
    private void loadDoctorDetails(int doctorId) {
        currentDoctor = Doctor.getDoctorById(doctorId);
        if (currentDoctor != null) {
            tfName.setText(currentDoctor.getD_name());
            tfGender.setText(currentDoctor.getD_gender());
            tfPhone.setText(currentDoctor.getD_phone());
            tfCard.setText(currentDoctor.getD_card());
            tfAddress.setText(currentDoctor.getD_address());
            tfPost.setText(currentDoctor.getD_post());
            tfIntroduction.setText(currentDoctor.getD_introduction());
            tfSection.setText(currentDoctor.getD_section());
            tfPrice.setText(String.valueOf(currentDoctor.getD_price()));
            tfHospital.setText(currentDoctor.getD_hospital());
        } else {
            clearFields();
        }
    }

    // 保存修改
    private void saveChanges() {
        if (currentDoctor != null) {
            currentDoctor.updateDoctorDetails(
                    tfName.getText().trim(),
                    tfGender.getText().trim(),
                    tfPhone.getText().trim(),
                    tfCard.getText().trim(),
                    tfAddress.getText().trim(),
                    tfPost.getText().trim(),
                    tfIntroduction.getText().trim(),
                    tfSection.getText().trim(),
                    Integer.parseInt(tfPrice.getText().trim()),
                    tfHospital.getText().trim()
            );
            currentDoctor.saveChangesToDatabase();
            showMessageDialog("Changes saved successfully.");
        }
    }

    // 清空文本框
    private void clearFields() {
        tfName.setText("");
        tfGender.setText("");
        tfPhone.setText("");
        tfCard.setText("");
        tfAddress.setText("");
        tfPost.setText("");
        tfIntroduction.setText("");
        tfSection.setText("");
        tfPrice.setText("");
        tfHospital.setText("");
    }

    // 显示消息对话框
    private void showMessageDialog(String message) {
        Frame messageFrame = new Frame("Update Result");
        Label label = new Label(message, Label.CENTER);
        messageFrame.add(label);
        messageFrame.setSize(300, 100);
        messageFrame.setVisible(true);

        // 处理窗口关闭事件
        messageFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                messageFrame.dispose(); // 关闭消息窗口
            }
        });
    }

    public static void main(String[] args) {
        new DoctorDetailsGUI();
    }
}
