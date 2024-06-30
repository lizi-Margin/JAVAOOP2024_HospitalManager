package shc;
import java.sql.*;

public class DatabaseManager  {
    String url = "jdbc:mysql://localhost:3306/javadb";
    String user = "root";
    String password = "114514";

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public DatabaseManager (){}
    public DatabaseManager(String userName , String password){
        this.user = userName;
        this.password = password;
    }
    public DatabaseManager(String userName , String password,String url){
        this.user = userName ;
        this.password = password;
        this.url = url;
    }
    // 构造函数：连接到数据库
    public int initConnection() {
        if (user == null || password == null || url==null) return -1;
        int state  =  0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("连接数据库...");
            connection = DriverManager.getConnection(url, user, password);
        }catch (SQLException se) {
            se.printStackTrace();
            connection = null;
            state = 1;
        }catch (ClassNotFoundException ce) {
            ce.printStackTrace();
            connection = null;
            state = 2;
        }
        return state;
    }




    // 执行查询
    public ResultSet executeQuery(String sql) {
        try {
            System.out.println("创建语句...");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (Exception e) {
            e.printStackTrace();
            resultSet = null;
        }
        return resultSet;
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
            statement = connection.prepareStatement(sql);
            rowsAffected = statement.executeUpdate(sql);

        } catch (Exception e) {
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
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 关闭连接
    public void close() {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

}
