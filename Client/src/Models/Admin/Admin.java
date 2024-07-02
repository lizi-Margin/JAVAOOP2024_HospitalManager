package src.Models.Admin;

import java.sql.Timestamp;
import src.Client;
import java.time.LocalDateTime;
import src.DatabaseManager.DatabaseManager;

public class Admin {
    private int a_id;              // 账号，主键
    private String a_password;     // 密码
    private String a_name;         // 姓名
    private String a_gender;       // 性别
    private String a_card;         // 身份证号
    private String a_phone;        // 联系方式
    private String a_address;      // 住址

    // 构造函数
    public Admin(int a_id, String a_password, String a_name, String a_gender, String a_card, String a_phone, String a_address) {
        this.a_id = a_id;
        this.a_password = a_password;
        this.a_name = a_name;
        this.a_gender = a_gender;
        this.a_card = a_card;
        this.a_phone = a_phone;
        this.a_address = a_address;
    }

    // Getter 和 Setter 方法
    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getA_password() {
        return a_password;
    }

    public void setA_password(String a_password) {
        this.a_password = a_password;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getA_gender() {
        return a_gender;
    }

    public void setA_gender(String a_gender) {
        this.a_gender = a_gender;
    }

    public String getA_card() {
        return a_card;
    }

    public void setA_card(String a_card) {
        this.a_card = a_card;
    }

    public String getA_phone() {
        return a_phone;
    }

    public void setA_phone(String a_phone) {
        this.a_phone = a_phone;
    }

    public String getA_address() {
        return a_address;
    }

    public void setA_address(String a_address) {
        this.a_address = a_address;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "a_id=" + a_id +
                ", a_password='" + a_password + '\'' +
                ", a_name='" + a_name + '\'' +
                ", a_gender='" + a_gender + '\'' +
                ", a_card='" + a_card + '\'' +
                ", a_phone='" + a_phone + '\'' +
                ", a_address='" + a_address + '\'' +
                '}';
    }

    // 记录操作
    private static void recordAction(int recordObject, int recordType) {
        Timestamp recordTime = Timestamp.valueOf(LocalDateTime.now());
        String sql = "INSERT INTO record (record_time, record_object, record_type) VALUES ('" + recordTime + "', " + recordObject + ", " + recordType + ")";
        Client.dbManager.executeUpdate(sql);
    }

    // 修改管理员信息
    public static void updateAdminInfo(int a_id, String a_name, String a_gender, String a_card, String a_phone, String a_address) {
        String sql = "UPDATE admin SET a_name = '" + a_name + "', a_gender = '" + a_gender + "', a_card = '" + a_card + "', a_phone = '" + a_phone + "', a_address = '" + a_address + "' WHERE a_id = " + a_id;
        Client.dbManager.executeUpdate(sql);
        recordAction(0, 0); // 记录修改操作
    }

    // 修改自己的密码
    public static void updatePassword(int a_id, String newPassword) {
        String sql = "UPDATE admin SET a_password = '" + newPassword + "' WHERE a_id = " + a_id;
        Client.dbManager.executeUpdate(sql);
        recordAction(0, 0); // 记录修改操作
    }

    // 删除管理员信息
    public static void deleteAdmin(int a_id) {
        String sql = "DELETE FROM admin WHERE a_id = " + a_id;
        Client.dbManager.executeUpdate(sql);
        recordAction(0, 1); // 记录删除操作
    }

    // 审核管理员注册
    public static void approveAdmin(int a_id) {
        String sql = "UPDATE admin SET a_state = TRUE WHERE a_id = " + a_id;
        Client.dbManager.executeUpdate(sql);
    }

    // 修改患者信息
    public static void updatePatientInfo(int p_id, String p_name, String p_gender, int p_age, String p_phone, String p_card, String p_address, String p_history) {
        String sql = "UPDATE patient SET p_name = '" + p_name + "', p_gender = '" + p_gender + "', p_age = " + p_age + ", p_phone = '" + p_phone + "', p_card = '" + p_card + "', p_address = '" + p_address + "', p_history = '" + p_history + "' WHERE p_id = " + p_id;
        Client.dbManager.executeUpdate(sql);
        recordAction(1, 0); // 记录修改操作
    }

    // 删除患者信息
    public static void deletePatient(int p_id) {
        // 删除与该患者相关的安排
        String sqlArrange = "DELETE FROM arrange WHERE p_id = " + p_id;
        Client.dbManager.executeUpdate(sqlArrange);

        // 删除与该患者相关的订单
        String sqlOrders = "DELETE FROM orders WHERE p_id = " + p_id;
        Client.dbManager.executeUpdate(sqlOrders);

        // 删除患者信息
        String sqlPatient = "DELETE FROM patient WHERE p_id = " + p_id;
        Client.dbManager.executeUpdate(sqlPatient);

        recordAction(1, 1); // 记录删除操作
    }

    // 审核患者注册
    public static void approvePatient(int p_id) {
        String sql = "UPDATE patient SET p_state = TRUE WHERE p_id = " + p_id;
        Client.dbManager.executeUpdate(sql);
    }

    // 修改医生信息
    public static void updateDoctorInfo(int d_id, String d_name, String d_gender, String d_phone, String d_card, String d_address, String d_post, String d_introduction, String d_section, double d_price, String d_hospital) {
        String sql = "UPDATE doctor SET d_name = '" + d_name + "', d_gender = '" + d_gender + "', d_phone = '" + d_phone + "', d_card = '" + d_card + "', d_address = '" + d_address + "', d_post = '" + d_post + "', d_introduction = '" + d_introduction + "', d_section = '" + d_section + "', d_price = " + d_price + ", d_hospital = '" + d_hospital + "' WHERE d_id = " + d_id;
        Client.dbManager.executeUpdate(sql);
        recordAction(2, 0); // 记录修改操作
    }

    // 删除医生信息
    public static void deleteDoctor(int d_id) {
        // 删除与该医生相关的安排
        String sqlArrange = "DELETE FROM arrange WHERE d_id = " + d_id;
        Client.dbManager.executeUpdate(sqlArrange);

        // 删除与该医生相关的订单
        String sqlOrders = "DELETE FROM orders WHERE d_id = " + d_id;
        Client.dbManager.executeUpdate(sqlOrders);

        // 删除与该医生相关的门诊信息
        String sqlInformation = "DELETE FROM information WHERE d_id = " + d_id;
        Client.dbManager.executeUpdate(sqlInformation);

        // 删除医生信息
        String sqlDoctor = "DELETE FROM doctor WHERE d_id = " + d_id;
        Client.dbManager.executeUpdate(sqlDoctor);

        recordAction(2, 1); // 记录删除操作
    }

    // 审核医生注册
    public static void approveDoctor(int d_id) {
        String sql = "UPDATE doctor SET d_state = TRUE WHERE d_id = " + d_id;
        Client.dbManager.executeUpdate(sql);
    }
}
