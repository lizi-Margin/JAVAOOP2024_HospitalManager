package src.Models.Admin.doctorpage;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddInformation extends Frame implements ActionListener {
    private TextField idField, dateField, priceField, departmentIdField;
    private Checkbox cycleCheckBox;
    private Button addButton;

    public AddInformation() {
        setTitle("添加信息到数据库");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        Label idLabel = new Label("ID:");
        idField = new TextField(10);

        Label dateLabel = new Label("date:");
        dateField = new TextField(10);

        Label priceLabel = new Label("price:");
        priceField = new TextField(10);

        Label departmentIdLabel = new Label("doctor ID:");
        departmentIdField = new TextField(10);

        cycleCheckBox = new Checkbox("cycle");

        addButton = new Button("add");
        addButton.addActionListener(this);

        add(idLabel);
        add(idField);
        add(dateLabel);
        add(dateField);
        add(priceLabel);
        add(priceField);
        add(departmentIdLabel);
        add(departmentIdField);
        add(cycleCheckBox);
        add(new Label()); // 空白Label占位
        add(addButton);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            // 获取输入的值
            int info_id = Integer.parseInt(idField.getText());
            String info_date = dateField.getText();
            int info_price = Integer.parseInt(priceField.getText());
            int d_id = Integer.parseInt(departmentIdField.getText());
            boolean info_cycle = cycleCheckBox.getState();
            boolean info_state = false; // 默认设置为 false

            // 调用数据库插入方法
            addInformationToDB(info_id, info_date, info_price, d_id, info_cycle, info_state);

            // 清空表单
            idField.setText("");
            dateField.setText("");
            priceField.setText("");
            departmentIdField.setText("");
            cycleCheckBox.setState(false);
        }
    }

    public static void addInformationToDB(int info_id, String info_date, int info_price, int d_id, boolean info_cycle, boolean info_state) {
        // 数据库连接参数
        String url = "jdbc:mysql://10.29.166.88:3306/javadb";
        String user = "username";
        String password = "password";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // 连接数据库
            connection = DriverManager.getConnection(url, user, password);

            // SQL 插入语句
            String sql = "INSERT INTO information (info_id, info_date, info_price, d_id, info_cycle, info_state) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            // 创建 PreparedStatement 对象
            statement = connection.prepareStatement(sql);
            statement.setInt(1, info_id);
            statement.setString(2, info_date);
            statement.setInt(3, info_price);
            statement.setInt(4, d_id);
            statement.setBoolean(5, info_cycle);
            statement.setBoolean(6, info_state);

            // 执行 SQL 语句
            statement.executeUpdate();
            System.out.println("信息成功添加到数据库。");

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // 关闭连接和 Statement 对象
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new AddInformation();
    }
}

