package src.Models.Admin;
import java.sql.*;

import src.DatabaseManager.DatabaseManager;
public class Information {
    private int info_id;         // 主键 id
    private String info_date;    // 日期
    private int info_price;      // 挂号费用
    private int d_id;            // 医生账号，外键
    private boolean info_cycle;  // 是否有周期性
    private boolean info_state;  // 审核状态

    // 构造函数
    public Information(int info_id, String info_date, int info_price, int d_id, boolean info_cycle, boolean info_state) {
        this.info_id = info_id;
        this.info_date = info_date;
        this.info_price = info_price;
        this.d_id = d_id;
        this.info_cycle = info_cycle;
        this.info_state = info_state;
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

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接和 Statement 对象
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 从数据库中删除信息
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

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接和 Statement 对象
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // Getter 和 Setter 方法
    public int getInfo_id() {
        return info_id;
    }

    public void setInfo_id(int info_id) {
        this.info_id = info_id;
    }

    public String getInfo_date() {
        return info_date;
    }

    public void setInfo_date(String info_date) {
        this.info_date = info_date;
    }

    public int getInfo_price() {
        return info_price;
    }

    public void setInfo_price(int info_price) {
        this.info_price = info_price;
    }

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public boolean isInfo_cycle() {
        return info_cycle;
    }

    public void setInfo_cycle(boolean info_cycle) {
        this.info_cycle = info_cycle;
    }

    public boolean isInfo_state() {
        return info_state;
    }

    public void setInfo_state(boolean info_state) {
        this.info_state = info_state;
    }
}
