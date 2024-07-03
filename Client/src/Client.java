package  src;
import src.DatabaseManager.*;
import src.Models.Admin.Pages.AdminMainPage;
import src.Models.Login;
import  src.Models.Register;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import src.Models.Admin.doctorpage.FunctionOfDoctor;
import src.Models.Patient.AppointmentSystem;
import src.Models.Admin.Admin;


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
    public static final int RegisterModel = 4;

    public static volatile int setCurrentModle = NULL;
    private static int currentModle = NULL;
    private static  final Lock lock = new ReentrantLock();
    private  static  final Condition condition = lock.newCondition();

    public static final int  nullUserId = -1 ;
    public static final String  nullUserType = "" ;
    public static int  userId =nullUserId;
    public static String  userType =nullUserType;
    public static void setUser(int id,String Type){
        if (id>=0 && (!Type.isEmpty())){
            userId = id;
            userType = Type;
        }
        System.out.print(userId);
        System.out.print("  ");
        System.out.println(userType);
    }
    public static void releaseUser(){
        userId = nullUserId;
        userType = nullUserType;
    }
    public static boolean hasUser(){
        if (userId == nullUserId || userType.equals( nullUserType) )
            return  false;
        if (userId >= 0 && (!userType.isEmpty()))
            return  true;
        return  false;
    }


    public static DatabaseManager  dbManager;
    private  static final String DB_URL = "jdbc:mysql://10.29.166.88:3306/javadb";
    private  static final String USER = "username";
    private  static final String PASS = "password";



    private static  int initDbManager(){
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

    public static void run(){
        if (initDbManager() != 0 ){
            System.err.println("Client failed to stat.");
            return;
        }

        setCurrentModle = LoginModel;
        runService();
        while(currentModle !=NULL || setCurrentModle != NULL){
            System.out.print(setCurrentModle);
            System.out.print("  ");
            System.out.println(currentModle);
            lock.lock();
            try{
                condition.await();
                runService();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        }
    }


    private static void runService(){
        lock.lock();

            if (setCurrentModle == EXIt)
                System.exit(0);
            if (setCurrentModle == currentModle || setCurrentModle == NULL)
                return;

            System.out.print(setCurrentModle);
            System.out.print("  ");
            System.out.println(currentModle);

            if (setCurrentModle == LoginModel) {
                currentModle = LoginModel;
                new Login().setVisible(true);
            }
            if (setCurrentModle == RegisterModel) {
                currentModle = RegisterModel;
                new Register().setVisible(true);
            }
            if (setCurrentModle == AdminModel) {
                currentModle = AdminModel;
                AdminMainPage adminMainPage = new AdminMainPage();
                adminMainPage.setVisible(true);
            }
            if (setCurrentModle == DoctorModel) {
                currentModle = DoctorModel;
                new FunctionOfDoctor();
            }
            if (setCurrentModle == PatientModel) {
                currentModle = PatientModel;
                new AppointmentSystem();
            }

            setCurrentModle = NULL;


    }

    public static void switchTo(int Model){
        lock.lock();
        try {
            setCurrentModle = Model;
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

//    private int runLogin(){
//
////        SwingUtilities.invokeLater(new Runnable() {
////            @Override
////            public void run() {
////                new Login().setVisible(true);
////            }
////        });
//
//        return 0;
//    }
//
}

