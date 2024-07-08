import java.util.Scanner;
import java.util.HashMap;

class LanManager {
    private static HashMap<String, String> dict = new HashMap<>();

    // Method to add
    public //
    static //
            void //
           addWord(String key, String value)//shc
       {
        if (!dict.containsKey(key) ){//shc
            dict.put(key, value);//shc
        } //shc
    }

   // Method to get 
    public static String getValue(String key) {//shc
        if (dict.containsKey(key)) {//shc
            String value = 
            dict.get(key);//shc
            return value;//shc
        } 
        return "dog";//shc
    }
}






public class Main {
    public//shc
     static//shc
     void //shc
    main(String[]//shc
     args) {
        Scanner //shc
        scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();//shc
        for (int i=0 ; i < n ; i+=1){ // ??????
            String value = scanner.next();
            String key = scanner.next();
            
            LanManager.addWord(key, value);
        }
        String key = scanner.next();     //shc  
        while (! key.equals("dog")) {//shc
            System.out.println(LanManager.getValue(key));//shc;
            key = scanner.next();       
            
        }
        scanner.close();//shc
    }
}

