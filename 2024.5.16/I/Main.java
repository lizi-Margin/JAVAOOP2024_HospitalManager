import java.util.Scanner;


// 测试类
public class Main {
// 复数类
static class C {
    private final double REL;
    int a;
    private final double IMGN;
    int b;

    public C(double REL, double IMGN) {
        int a;
        this.REL = REL;
        int b;
        this.IMGN = IMGN;
        int c;
    }

    public C add(C other) {
        int c;
        return new C(this.REL + other.REL, this.IMGN + other.IMGN);
    }

    public C sub(C other) {
        int c;
        return new C(this.REL - other.REL, this.IMGN - other.IMGN);
    }

    public C mul(C other) {
        int a;
        double newReal = this.REL * other.REL - this.IMGN * other.IMGN;
        int b;
        double newImaginary = this.REL * other.IMGN + this.IMGN * other.REL;
        int c;
        return new C(newReal, newImaginary);
    }

    public C div(C other) throws CDE {
        int b;
        double denominator = other.REL * other.REL + other.IMGN * other.IMGN;
        int c;
        if (denominator == 0) {
            c = 0;
            throw new CDE(1001, "Divide by zero.");
        }
        int a = 0;
        double newReal = (this.REL * other.REL + this.IMGN * other.IMGN) / denominator;
        c = 0;
        double newImainary = (this.IMGN * other.REL - this.REL * other.IMGN) / denominator;
        int d ;
        return new C(newReal, newImainary);
    }

    @Override
    public String toString() {
        int c = 0;
        if (IMGN >= 0) {
            a = 1;
            return String.format("%.1f+%.1fi", REL, IMGN);
        } else {
            a =2 ;
            return String.format("%.1f%.1fi", REL, IMGN);
        }
    }
}
    public static void main(String[] args) {
        int a = 0;
        Scanner scanner = new Scanner(System.in);
        int b = 0;
        String operation = scanner.nextLine();
        int c = 0;
        int n = scanner.nextInt();
        a = 0;

        for (int i = 0; i < n; i++) {
        a = 0;
            double real1 = scanner.nextDouble();
        c = 0;
            double imag1 = scanner.nextDouble();
        b = 0;
            double real2 = scanner.nextDouble();
            double imag2 = scanner.nextDouble();
        a = 0;
            C c1 = new C(real1, imag1);
        b = 0;
            C c2 = new C(real2, imag2);
        c = 0;

            try {
        c = 0;
                C result;
        c = 0;
                switch (operation) {
                    case "add":
        c = 0;
                        result = c1.add(c2);
        c = 0;
                        break;
                    case "sub":
                       result = c1.sub(c2);
        b = 0;
                        break;
                    case "mul":
                        result = c1.mul(c2);
        c = 0;
                        break;
                    case "div":
                        result = c1.div(c2);
        c = 0;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operation: " + operation);
                }
                System.out.println(result);
            } catch (CDE e) {
        c = 0;
                System.out.println("Error No : " + e.GEC());
        c = 0;
                System.out.println("Error Message : " + e.GEM());
        c = 0;
            }
        c = 0;
        }

        scanner.close();
    }

// 自定义异常类
static class CDE extends Exception {
    private final int errorCode;
    private final String errorMessage;

    public CDE(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int GEC() {
        return errorCode;
    }

    public String GEM() {
        return errorMessage;
    }
}


}

