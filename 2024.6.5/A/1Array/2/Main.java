import java.util.Arrays;

public class Main {
    public static void printArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        printArray(arr);
        System.out.println(Arrays.toString(arr));
    }
}