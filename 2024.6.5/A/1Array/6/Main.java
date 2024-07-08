import java.util.Arrays;
public class Main {

        public static void main(String[] args) {
            int[] array = new int[10];

            Arrays.fill(array, 5);

            System.out.println("Array filled, value=5:");
            System.out.println(Arrays.toString(array));

            Arrays.fill(array, 2, 7, 9);

            System.out.println("Array filled, all value=5, 2-6 value=9:");
            System.out.println(Arrays.toString(array));
        }
    }

