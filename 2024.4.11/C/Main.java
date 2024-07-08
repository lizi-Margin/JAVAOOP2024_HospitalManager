import java.util.Scanner;
import java.util.InputMismatchException;
public class Main {


    public static void main(String[] args) {
        int T;
        Scanner sc = new Scanner(System.in);
        try { T = sc.nextInt();}catch(InputMismatchException e){
            System.out.print("Input data error");
            sc.close();
            return;            
        }
         if (T<1 || T > 10){
                System.out.print("Input data error");
                sc.close();
                return;
 
        }
        int A ,B;
        
        for (int i = 0; i < T; i++) {
            try{
             A = sc.nextInt();
         B = sc.nextInt();}catch(InputMismatchException e){
            System.out.print("Input data error");
            sc.close();
            return;            
         }
            
            // 检查输入数据范围是否正确
            if (A < 0 || B < 0 || A > 1018 || B > 1018) {
                System.out.print("Input data error");
                sc.close();
                return;
            }
            
            // 判断是否存在区间[l,r]
            if ( Math.abs(A-B) <2 ) {
                    System.out.println("MM");

            } else {
                    System.out.println("GG");
            }

        }
        sc.close();
    }
}

