package src.Models.Admin;

import src.DatabaseManager.DatabaseManager;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {

    private  static final String DB_URL = "jdbc:mysql://10.29.166.88:3306/javadb";
    private  static final String USER = "username";
    private  static final String PASS = "password";
    public static DatabaseManager  dbManager;
    public static int record_ID_number = 1; // 全局变量，初始值为1

    private static int initDbManager(){
        int state = 0;
        dbManager = new DatabaseManager(USER,PASS,DB_URL);
        int connFailedCounter = 0;
        state = dbManager.initConnection();
        while ( state != 0 && connFailedCounter <= 49){
            connFailedCounter +=1;
            System.out.print("connFailed, tryTimes=");
            System.out.print(connFailedCounter);
            state = dbManager.initConnection();
        }
        return state;
    }

    public static void main(String[] args)
    {
        int state = initDbManager();

        if (state == 0) {

            Doctor doctor = Doctor.getDoctorById(5);
            doctor.updateDoctorPassword("123456");
            doctor.saveChangesToDatabase();


            dbManager.close();
        }
        else
        {
            System.out.println("Failed to connect to the database.");
        }
    }
}