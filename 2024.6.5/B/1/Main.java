class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}


public class Main {
    public static void main(String[] args) {
        try {
            throw new MyException("MyException occurs.");
        } catch (MyException e) {
            System.out.println("Eception caught: " + e.getMessage());
        }
    }
}