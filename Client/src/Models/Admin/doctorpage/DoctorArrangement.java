package src.Models.Admin.doctorpage;

import src.Client;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DoctorArrangement extends Frame implements ActionListener {
    private TextField doctorIdField;
    private static TextArea resultArea;
    private Button queryButton;

    public DoctorArrangement() {
        setTitle("查询医生安排信息");
        setSize(500, 400);
        setLayout(new BorderLayout());

        Panel topPanel = new Panel(new FlowLayout());
        Label doctorIdLabel = new Label("doctor ID:");
        doctorIdField = new TextField(10);
        topPanel.add(doctorIdLabel);
        topPanel.add(doctorIdField);

        Panel middlePanel = new Panel(new BorderLayout());
        resultArea = new TextArea();
        middlePanel.add(new Label("result:"), BorderLayout.NORTH);
        middlePanel.add(resultArea, BorderLayout.CENTER);

        Panel bottomPanel = new Panel(new FlowLayout());
        queryButton = new Button("search");
        queryButton.addActionListener(this);
        bottomPanel.add(queryButton);

        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        doctorIdField.setText(String.format("%d", Client.userId));
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == queryButton) {
            // 获取输入的医生ID
            int doctorId = Integer.parseInt(doctorIdField.getText());

            // 调用数据库查询方法
            getArrangementByDoctorId(doctorId);
        }
    }

    public static void getArrangementByDoctorId(int doctorId) {
        String DB_URL = "jdbc:mysql://10.29.166.88:3306/javadb";
        String USER = "your_username";
        String PASS = "your_password";

        String sql = "SELECT * FROM arrange WHERE d_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, doctorId);
            ResultSet resultSet = statement.executeQuery();

            StringBuilder resultBuilder = new StringBuilder();
            while (resultSet.next()) {
                int ar_id = resultSet.getInt("ar_id");
                String ar_time = resultSet.getString("ar_time");
                int d_id = resultSet.getInt("d_id");
                int p_id = resultSet.getInt("p_id");
                int info_id = resultSet.getInt("info_id");

                // 将查询结果添加到字符串构建器中
                resultBuilder.append("ar_id: ").append(ar_id).append(", ar_time: ").append(ar_time)
                        .append(", d_id: ").append(d_id).append(", p_id: ").append(p_id)
                        .append(", info_id: ").append(info_id).append("\n");
            }

            // 显示查询结果到界面上的TextArea中
            resultArea.setText(resultBuilder.toString());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DoctorArrangement();
    }
}
