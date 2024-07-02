package src.Models.Admin;

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
