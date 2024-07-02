package  src;
import src.DatabaseManager.*;
import src.Models.Login;

import javax.swing.*;

/*
    shc 2024
    readme:
    1.需要连接服务端跟shc说，并且需要把DB_URL中的localhost换成shc的ip地址"10.29.166.88"
    2.连接数据库的功能已经实现，在initDbManager()中。
    3.访问数据库需要用到sql，请使用:
        dbManager.executeQuery(sql);
        dbManager.executeUpdate(sql);
 */

public class Client {

    public static final int NULL = -1;
    public static final int EXIt = -2;
    public static final int LoginModel = 0;
    public static final int AdminModel = 1;
    public static final int DoctorModel = 2;
    public static final int PatientModel = 3;

    public static int setCurrentModle = NULL;
    private static int currentModle = NULL;

    private User user;
    public static DatabaseManager  dbManager;
    private  static final String DB_URL = "jdbc:mysql://localhost:3306/javadb";
    private  static final String USER = "username";
    private  static final String PASS = "password";


    Client(){
        run();
    }

    private int initDbManager(){
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

    public void run(){
        if (initDbManager() != 0 ){
            System.err.println("Client failed to stat.");
            return;
        }



        setCurrentModle = LoginModel;
        while(currentModle !=NULL || setCurrentModle != NULL){
            System.out.print(setCurrentModle);
            System.out.print("  ");
            System.out.println(currentModle);
            runService();
        }
    }

    private int runLogin(){

//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new Login().setVisible(true);
//            }
//        });
        new Login().setVisible(true);
        return 0;
    }

    private void runService(){
        if (setCurrentModle == EXIt){
            System.exit(0);
            return;}
        if (setCurrentModle == currentModle || setCurrentModle == NULL)
            return;

        System.out.print(setCurrentModle);
        System.out.print("  ");
        System.out.println(currentModle);

        if (setCurrentModle==LoginModel){
            currentModle = LoginModel ;
            this.runLogin();
        }

        if (setCurrentModle==AdminModel){
            currentModle = AdminModel ;
        }
        if (setCurrentModle==DoctorModel){
            currentModle = DoctorModel ;
        }
        if (setCurrentModle==PatientModel){
            currentModle = PatientModel ;
        }

        setCurrentModle = NULL;
    }

}
