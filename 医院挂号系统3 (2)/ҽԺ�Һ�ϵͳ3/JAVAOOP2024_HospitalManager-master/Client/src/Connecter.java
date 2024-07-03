package src;

import src.DatabaseManager.DatabaseManager;

public class Connecter {

    public static void main(String[] args) {
        initDbManager();

        /* ... */
    }



    public static DatabaseManager dbManager;
    private  static final String DB_URL = "jdbc:mysql://10.29.166.88:3306/javadb";
    private  static final String USER = "username";
    private  static final String PASS = "password";


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
}
