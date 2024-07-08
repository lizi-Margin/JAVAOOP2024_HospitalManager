import java.util.Scanner;
import java.util.*;


public class Main {

    static Set<Integer> A = new HashSet<>();
    static Set<Integer> AorB = new HashSet<>();
    static Set<Integer> AnotB = new HashSet<>();
    static Set<Integer> AandB = new HashSet<>();
    static void print(Set<Integer> set){
        set.stream() // 将集合转换为流
                .sorted() // 对流进行排序
                .forEach(i->{
                    System.out.print(i);
                    System.out.print(" ");
                });
        // int n = set.size() ;
        // for (Integer number : set) {
        //     n--;
        //     System.out.print(number );
        //     if (n>0)System.out.print(" ");
        // }
        System.out.println();
    }
//shc
    public//shc
     static//shc
     void //shc
    main(String[]//shc
     args) {
        Scanner //shc
        scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();//shc
        for (int i=0 ; i < n ; i+=1){ // ??????
            int num = scanner.nextInt();
            Main.A.add(num);
            Main.AorB.add(num);
            Main.AnotB.add(num);
        }
        n = scanner.nextInt();//shc
        for (int i=0 ; i < n ; i+=1){ // ??????
            int num = scanner.nextInt();            
            Main.AorB.add(num);
            Main.AnotB.remove(num);             
            if ( Main.A.contains(num)){ Main.AandB.add(num); }
        }
        Main.print(AandB);
        Main.print(AorB);
        Main.print(AnotB);
        
        
        scanner.close();//shc
    }
}

