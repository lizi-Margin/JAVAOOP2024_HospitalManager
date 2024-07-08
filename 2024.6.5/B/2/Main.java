import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                runProgram();
                break;
            } catch (Exception e) {
                System.out.println("捕获到异常: " + e.getMessage());
                System.out.println("重新启动程序...");
            }
        }
        scanner.close();;
    }

    public static void runProgram() throws Exception {
        
        System.out.print("请输入一个数字: ");
        int x = scanner.nextInt();
        System.out.println("你输入的数字是: " + x);
        if (x == 0) {
            throw new Exception("输入不能为0");
        }
        
    }
}