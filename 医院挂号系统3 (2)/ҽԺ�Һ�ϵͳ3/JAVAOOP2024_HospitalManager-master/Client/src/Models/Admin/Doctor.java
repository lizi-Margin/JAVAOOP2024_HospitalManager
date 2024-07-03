package src.Models.Admin;

import src.Client;

import java.sql.*;

public class Doctor {
    private int d_id;
    private String d_password;
    private String d_name;
    private String d_gender;
    private String d_phone;
    private String d_card;
    private String d_address;
    private String d_post;
    private String d_introduction;
    private String d_section;
    private int d_state;
    private int d_price;
    private String d_hospital;

    public Doctor(int d_id, String d_password, String d_name, String d_gender, String d_phone, String d_card, String d_address, String d_post, String d_introduction, String d_section, int d_state, int d_price, String d_hospital) {
        this.d_id = d_id;
        this.d_password = d_password;
        this.d_name = d_name;
        this.d_gender = d_gender;
        this.d_phone = d_phone;
        this.d_card = d_card;
        this.d_address = d_address;
        this.d_post = d_post;
        this.d_introduction = d_introduction;
        this.d_section = d_section;
        this.d_state = d_state;
        this.d_price = d_price;
        this.d_hospital = d_hospital;
    }

    // 方法用于一次性修改多个属性
    public void updateDoctorDetails(String d_name, String d_gender, String d_phone, String d_card, String d_address, String d_post, String d_introduction, String d_section, int d_price, String d_hospital) {
        this.d_name = d_name;
        this.d_gender = d_gender;
        this.d_phone = d_phone;
        this.d_card = d_card;
        this.d_address = d_address;
        this.d_post = d_post;
        this.d_introduction = d_introduction;
        this.d_section = d_section;
        this.d_price = d_price;
        this.d_hospital = d_hospital;
    }

    public void updateDoctorPassword(String newPassword) {
        this.d_password = newPassword;
    }

    public void updatePassword(String newPassword) {
        // 调用已有的更新密码方法
        updateDoctorPassword(newPassword);

        // 保存修改后的对象到数据库
        saveChangesToDatabase();
    }
    // 从数据库中根据医生ID获取医生对象的静态方法
    public static Doctor getDoctorById(int doctorId) {
        Doctor doctor = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://10.29.166.88:3306/javadb", "username", "password");

            String sql = "SELECT * FROM doctor WHERE d_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, doctorId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                doctor = new Doctor(
                        rs.getInt("d_id"),
                        rs.getString("d_password"),
                        rs.getString("d_name"),
                        rs.getString("d_gender"),
                        rs.getString("d_phone"),
                        rs.getString("d_card"),
                        rs.getString("d_address"),
                        rs.getString("d_post"),
                        rs.getString("d_introduction"),
                        rs.getString("d_section"),
                        rs.getInt("d_state"),
                        rs.getInt("d_price"),
                        rs.getString("d_hospital")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return doctor;
    }

    // toString 方法用于调试和打印对象信息
    @Override
    public String toString() {
        return "Doctor{" +
                "d_id=" + d_id +
                ", d_name='" + d_name + '\'' +
                ", d_gender='" + d_gender + '\'' +
                ", d_phone='" + d_phone + '\'' +
                ", d_card='" + d_card + '\'' +
                ", d_address='" + d_address + '\'' +
                ", d_post='" + d_post + '\'' +
                ", d_introduction='" + d_introduction + '\'' +
                ", d_section='" + d_section + '\'' +
                ", d_state=" + d_state +
                ", d_price=" + d_price +
                ", d_hospital='" + d_hospital + '\'' +
                '}';
    }

    public void saveChangesToDatabase() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://10.29.166.88:3306/javadb", "username", "password");

            String sql = "UPDATE doctor SET d_name=?, d_gender=?, d_phone=?, d_card=?, d_address=?, d_post=?, d_introduction=?, d_section=?, d_price=?, d_hospital=?,d_password=? WHERE d_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.d_name);
            stmt.setString(2, this.d_gender);
            stmt.setString(3, this.d_phone);
            stmt.setString(4, this.d_card);
            stmt.setString(5, this.d_address);
            stmt.setString(6, this.d_post);
            stmt.setString(7, this.d_introduction);
            stmt.setString(8, this.d_section);
            stmt.setInt(9, this.d_price);
            stmt.setString(10, this.d_hospital);
            stmt.setString(11, this.d_password);
            stmt.setInt(12, this.d_id);


            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Changes have been saved to the database.");
            } else {
                System.out.println("No changes were made.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getD_gender() {
        return d_gender;
    }

    public void setD_gender(String d_gender) {
        this.d_gender = d_gender;
    }

    public String getD_phone() {
        return d_phone;
    }

    public void setD_phone(String d_phone) {
        this.d_phone = d_phone;
    }

    public String getD_card() {
        return d_card;
    }

    public void setD_card(String d_card) {
        this.d_card = d_card;
    }

    public String getD_address() {
        return d_address;
    }

    public void setD_address(String d_address) {
        this.d_address = d_address;
    }

    public String getD_post() {
        return d_post;
    }

    public void setD_post(String d_post) {
        this.d_post = d_post;
    }

    public String getD_introduction() {
        return d_introduction;
    }

    public void setD_introduction(String d_introduction) {
        this.d_introduction = d_introduction;
    }

    public String getD_section() {
        return d_section;
    }

    public void setD_section(String d_section) {
        this.d_section = d_section;
    }

    public int getD_state() {
        return d_state;
    }

    public void setD_state(int d_state) {
        this.d_state = d_state;
    }

    public int getD_price() {
        return d_price;
    }

    public void setD_price(int d_price) {
        this.d_price = d_price;
    }

    public String getD_hospital() {
        return d_hospital;
    }

    public void setD_hospital(String d_hospital) {
        this.d_hospital = d_hospital;
    }


}