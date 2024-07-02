package src.Models.Admin;

public class Doctor {
    private int d_id;               // 账号，主键
    private String d_password;      // 密码
    private String d_name;          // 姓名
    private String d_gender;        // 性别
    private String d_phone;         // 联系方式
    private String d_card;          // 身份证号
    private String d_address;       // 住址
    private String d_post;          // 职称
    private String d_introduction;  // 简介
    private String d_section;       // 科室
    private int d_state;            // 状态
    private int d_price;            // 挂号价格
    private String d_hospital;      // 所在医院
    private boolean d_approved;     // 注册审核状态

    // 构造函数
    public Doctor(int d_id, String d_password, String d_name, String d_gender, String d_phone, String d_card, String d_address, String d_post, String d_introduction, String d_section, int d_state, int d_price, String d_hospital, boolean d_approved) {
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
        this.d_approved = d_approved;
    }

    // Getter 和 Setter 方法
    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public String getD_password() {
        return d_password;
    }

    public void setD_password(String d_password) {
        this.d_password = d_password;
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

    public boolean isD_approved() {
        return d_approved;
    }

    public void setD_approved(boolean d_approved) {
        this.d_approved = d_approved;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "d_id=" + d_id +
                ", d_password='" + d_password + '\'' +
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
                ", d_approved=" + d_approved +
                '}';
    }
}
