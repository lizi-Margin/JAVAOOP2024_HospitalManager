package src.Models.Admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;


public class Arrange {
    private int ar_id;      // 主键 id
    private String ar_time; // 出诊时间
    private int d_id;       // 医生账号，外键
    private int p_id;       // 患者账号，外键
    private int info_id;    // 门诊 id，外键

    // 构造函数
    public Arrange(int ar_id, String ar_time, int d_id, int p_id, int info_id) {
        this.ar_id = ar_id;
        this.ar_time = ar_time;
        this.d_id = d_id;
        this.p_id = p_id;
        this.info_id = info_id;
    }


    private static final String DB_URL = "jdbc:mysql://10.29.166.88:3306/javadb";
    private static final String USER = "your_username";
    private static final String PASS = "your_password";

    // 方法：插入新的 Arrange 记录
    public static boolean insertArrange(String ar_time, int d_id, int p_id, int info_id) {
        String sql = "INSERT INTO arrange (ar_time, d_id, p_id, info_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, ar_time);
            statement.setInt(2, d_id);
            statement.setInt(3, p_id);
            statement.setInt(4, info_id);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 方法：删除指定 ar_id 的 Arrange 记录
    public static boolean deleteArrange(int ar_id) {
        String sql = "DELETE FROM arrange WHERE ar_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, ar_id);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    // 方法：根据医生账号查询出诊信息
    public static void getArrangementByDoctorId(int doctorId) {
        String DB_URL = "jdbc:mysql://10.29.166.88:3306/javadb";
        String USER = "your_username";
        String PASS = "your_password";

        String sql = "SELECT * FROM arrange WHERE d_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, doctorId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int ar_id = resultSet.getInt("ar_id");
                String ar_time = resultSet.getString("ar_time");
                int d_id = resultSet.getInt("d_id");
                int p_id = resultSet.getInt("p_id");
                int info_id = resultSet.getInt("info_id");

                // 打印或处理查询结果
                System.out.println("ar_id: " + ar_id + ", ar_time: " + ar_time + ", d_id: " + d_id + ", p_id: " + p_id + ", info_id: " + info_id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Getter 和 Setter 方法
    public int getAr_id() {
        return ar_id;
    }

    public void setAr_id(int ar_id) {
        this.ar_id = ar_id;
    }

    public String getAr_time() {
        return ar_time;
    }

    public void setAr_time(String ar_time) {
        this.ar_time = ar_time;
    }

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getInfo_id() {
        return info_id;
    }

    public void setInfo_id(int info_id) {
        this.info_id = info_id;
    }
}
