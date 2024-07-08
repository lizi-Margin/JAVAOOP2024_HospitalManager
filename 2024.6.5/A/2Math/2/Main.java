public class Main {
    public static double getE(int terms) {
        double e = 1.0; 
        double factorial = 1.0; 

        for (int i = 1; i <= terms; i++) {
            factorial *= i;
            e += 1.0 / factorial; 
        }
        return e;
    }
    public static void main(String[] args) {
        double mathE = Math.E;
        double getE =getE(10); 
        double difference = Math.abs(getE - mathE);
        System.out.println("getE="+getE + "   mathE=" + mathE+ "   diff=" + difference);
    }
}