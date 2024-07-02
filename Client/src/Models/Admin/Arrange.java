package src.Models.Admin;

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
