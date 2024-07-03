package src.Models.Admin.doctorpage;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteInformation extends Frame implements ActionListener {
    private TextField idField;
    private Button deleteButton;

    public DeleteInformation() {
        setTitle("Delete Information");
        setSize(300, 150);
        setLayout(new FlowLayout());

        Label idLabel = new Label("ID:");
        idField = new TextField(10);

        deleteButton = new Button("Delete");
        deleteButton.addActionListener(this);

        add(idLabel);
        add(idField);
        add(deleteButton);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            // 获取输入的信息ID
            int info_id = Integer.parseInt(idField.getText());

            // 调用数据库删除方法
            deleteInformationFromDB(info_id);

            // 清空输入框
            idField.setText("");
        }
    }

    public static void deleteInformationFromDB(int info_id) {
        // 数据库连接参数
        String url = "jdbc:mysql://10.29.166.88:3306/javadb";
        String user = "username";
        String password = "password";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // 连接数据库
            connection = DriverManager.getConnection(url, user, password);

            // SQL 删除语句
            String sql = "DELETE FROM information WHERE info_id = ?";

            // 创建 PreparedStatement 对象
            statement = connection.prepareStatement(sql);
            statement.setInt(1, info_id);

            // 执行 SQL 语句
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("信息成功从数据库中删除。");
            } else {
                System.out.println("未找到符合条件的信息。");
            }

        }catch (SQLIntegrityConstraintViolationException sqlIe){
            System.out.println("该信息已被安排，无法删除。");
        }catch (SQLException ex) {
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
        new DeleteInformation();
    }
}
