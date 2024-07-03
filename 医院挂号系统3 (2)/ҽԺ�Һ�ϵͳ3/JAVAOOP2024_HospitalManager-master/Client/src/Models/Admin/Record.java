package src.Models.Admin;

public class Record {
    private int record_id;        // 主键 id
    private String record_time;   // 操作时间
    private int record_object;    // 操作对象类型（0：管理员，1：患者，2：医生）
    private int record_type;      // 操作类型（0：修改，1：删除，2：增加）

    // 构造函数
    public Record(int record_id, String record_time, int record_object, int record_type) {
        this.record_id = record_id;
        this.record_time = record_time;
        this.record_object = record_object;
        this.record_type = record_type;
    }

    // Getter 和 Setter 方法
    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public String getRecord_time() {
        return record_time;
    }

    public void setRecord_time(String record_time) {
        this.record_time = record_time;
    }

    public int getRecord_object() {
        return record_object;
    }

    public void setRecord_object(int record_object) {
        this.record_object = record_object;
    }

    public int getRecord_type() {
        return record_type;
    }

    public void setRecord_type(int record_type) {
        this.record_type = record_type;
    }
}
