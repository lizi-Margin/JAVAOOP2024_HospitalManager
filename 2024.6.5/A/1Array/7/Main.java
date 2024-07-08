import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] array2D = new int[10][10];
        for (int i = 0; i < array2D.length; i++) {
            for (int j = 0; j < array2D[i].length; j++) {
                array2D[i][j] = (int) (Math.random() * 100); 
            }
        }

        System.out.println("2D array:");
        print2DArray(array2D);

        int[] array1D = new int[array2D.length * array2D[0].length];
        int index = 0;
        for (int i = 0; i < array2D.length; i++) {
            System.arraycopy(array2D[i], 0, array1D, index, array2D[i].length);
            index += array2D[i].length;
        }

        Arrays.sort(array1D);

        index = 0;
        for (int i = 0; i < array2D.length; i++) {
            System.arraycopy(array1D, index, array2D[i], 0, array2D[i].length);
            index += array2D[i].length;
        }

        System.out.println("Sorted 2D array:");
        print2DArray(array2D);
    }

    public static void print2DArray(int[][] array) {
        for (int[] row : array) {
            System.out.println(Arrays.toString(row));
        }
    }
}

