package src.Models.Admin;

public class Patient {
    private int p_id;             // 账号，主键
    private String p_password;    // 密码
    private String p_name;        // 姓名
    private String p_gender;      // 性别
    private int p_age;            // 年龄
    private String p_phone;       // 联系方式
    private String p_card;        // 身份证号
    private String p_address;     // 住址
    private String p_history;     // 病历
    private boolean p_state;      // 注册审核状态

    // 构造函数
    public Patient(int p_id, String p_password, String p_name, String p_gender, int p_age, String p_phone, String p_card, String p_address, String p_history, boolean p_state) {
        this.p_id = p_id;
        this.p_password = p_password;
        this.p_name = p_name;
        this.p_gender = p_gender;
        this.p_age = p_age;
        this.p_phone = p_phone;
        this.p_card = p_card;
        this.p_address = p_address;
        this.p_history = p_history;
        this.p_state = p_state;
    }

    // Getter 和 Setter 方法
    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_password() {
        return p_password;
    }

    public void setP_password(String p_password) {
        this.p_password = p_password;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_gender() {
        return p_gender;
    }

    public void setP_gender(String p_gender) {
        this.p_gender = p_gender;
    }

    public int getP_age() {
        return p_age;
    }

    public void setP_age(int p_age) {
        this.p_age = p_age;
    }

    public String getP_phone() {
        return p_phone;
    }

    public void setP_phone(String p_phone) {
        this.p_phone = p_phone;
    }

    public String getP_card() {
        return p_card;
    }

    public void setP_card(String p_card) {
        this.p_card = p_card;
    }

    public String getP_address() {
        return p_address;
    }

    public void setP_address(String p_address) {
        this.p_address = p_address;
    }

    public String getP_history() {
        return p_history;
    }

    public void setP_history(String p_history) {
        this.p_history = p_history;
    }

    public boolean isP_state() {
        return p_state;
    }

    public void setP_state(boolean p_state) {
        this.p_state = p_state;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "p_id=" + p_id +
                ", p_password='" + p_password + '\'' +
                ", p_name='" + p_name + '\'' +
                ", p_gender='" + p_gender + '\'' +
                ", p_age=" + p_age +
                ", p_phone='" + p_phone + '\'' +
                ", p_card='" + p_card + '\'' +
                ", p_address='" + p_address + '\'' +
                ", p_history='" + p_history + '\'' +
                ", p_state=" + p_state +
                '}';
    }
}
