package shc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager implements DatabaseManagerIF {
    // 数据库连接信息
    private  String DB_URL ;
    private  String USER ;
    private  String PASS ;

    // 数据库连接对象
    private Connection conn;

    public DatabaseManager (){}
    public DatabaseManager(String userName , String password){
        setUser(userName, password);
    }
    public DatabaseManager(String userName , String password,String url){
        setUser(userName, password);
        setURL(url);
    }   
    // 构造函数：连接到数据库
    public int initConnection() {
        if (USER == null || PASS == null || DB_URL==null) return -1;
        int state  =  0;
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 打开连接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            state =  1; 
            e.printStackTrace();
        }
        return state;
    }



    public void setUser (String userName , String password){
        this.USER = userName ;
        this.PASS = password;
    }
    public void setURL(String url){
        DB_URL = url;
    }

    // 执行查询
    public ResultSet executeQuery(String sql) {
            ResultSet rs = null;
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();
            }catch(SQLException e){
                e.printStackTrace();
                rs = null;
            } 
            return rs;
    }
    public ResultSet executeQuery(String sql,int retry) {
        ResultSet rs = null;
        for (int i = 0 ; i < retry && rs==null ; i+=1){
            rs = executeQuery(sql);
        }
        return rs;
    }

    // 执行更新（增删改）
    public int executeUpdate(String sql)  {
            int rowsAffected = -1;
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                rowsAffected = stmt.executeUpdate();
            }catch(SQLException e){
                e.printStackTrace();
                rowsAffected = -1;
            } 
            return rowsAffected;

    }
    public int executeUpdate(String sql,int retry) {
        int rowsAffected = -1;
        for (int i = 0 ; i < retry && rowsAffected==-1 ; i+=1){
            rowsAffected = executeUpdate(sql);
        }
        return rowsAffected;
    }


    // 检测连接状态
    public boolean isConnected() {
        try {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 关闭连接
    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
