package src.Models.Admin.doctorpage;
import java.awt.*;
import java.awt.event.*;

import src.Client;
import src.Models.Admin.Doctor;

public class DoctorUpdatePassword extends Frame implements ActionListener {
    private TextField newPasswordField;
    private Button updateButton;

    public DoctorUpdatePassword() {
        // 设置窗口标题
        super("Update Doctor Password");

        // 创建界面元素
        Label newPasswordLabel = new Label("New Password:");
        newPasswordField = new TextField(20);
        updateButton = new Button("Update Password");

        // 设置布局
        setLayout(new GridLayout(2, 1));

        // 添加元素到窗口
        add(newPasswordLabel);
        add(newPasswordField);
        add(updateButton);

        // 注册按钮点击事件监听器
        updateButton.addActionListener(this);

        // 设置窗口大小和可见性
        setSize(300, 100);
        setVisible(true);

        // 窗口关闭时的操作
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose(); // 关闭当前窗口
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateButton) {
            // 获取输入的新密码
            String newPassword = newPasswordField.getText();

            // 获取当前登录的医生对象并更新密码
            Doctor doctor = Doctor.getDoctorById(Client.userId);
            if (doctor != null) {
                doctor.updateDoctorPassword(newPassword);
                doctor.saveChangesToDatabase();
                showMessageDialog("Password updated successfully.");
            } else {
                showMessageDialog("Error: Doctor not found.");
            }
        }
    }

    // 自定义方法，显示消息对话框
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
        new DoctorUpdatePassword();
    }
}
