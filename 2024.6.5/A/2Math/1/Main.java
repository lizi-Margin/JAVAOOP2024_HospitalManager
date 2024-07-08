import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        double randomNumber = random.nextDouble() * 100; // 生成 0 到 100 之间的随机数
        System.out.println("随机数: " + randomNumber);

        // 小数
        double fractionalPart = randomNumber - (int) randomNumber;
        System.out.println("小数为: " + fractionalPart);
        double roundedFractionalPart = Math.round(fractionalPart * 100.0) / 100.0;
        System.out.println("小数四舍五入: " + roundedFractionalPart);

        // 开方
        double squareRoot = Math.sqrt(randomNumber);
        System.out.println("开方: " + squareRoot);

        // 平方
        double square = Math.pow(randomNumber, 2);
        System.out.println("平方: " + square);

        System.out.println("pi=" + Math.PI);
        System.out.println("e=" + Math.E);
    }
}