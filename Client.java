import shc.*;

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
    private User user;
    private DatabaseManager  dbManager;
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


        int serviceInd = 1;
        while(serviceInd !=0){
            if (runLogin()!=0 ){
                System.err.println("Exited while loging in.");
                return;
            }

            serviceInd = runService();
        }
    }

    private int runLogin(){

        return 0;
    }

    private int runService(){

        return 0;
    }

}

