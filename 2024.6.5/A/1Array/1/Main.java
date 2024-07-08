import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] source = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] sys = systemCopy(source, 0, source.length-1);
        int[] copyOf = Arrays.copyOf(source, source.length-1);
        int[] copyOfLen = Arrays.copyOfRange(copyOf, 0, source.length-1);

        printArray(sys);
        printArray(copyOf);
        printArray(copyOfLen);
    }
    public static int[] systemCopy(int[] source, int start, int end) {
        int[] result = new int[end - start];
        System.arraycopy(source, start, result, 0, end - start);
        return result;
    }

    public static void printArray(int[] arr){
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    
}
