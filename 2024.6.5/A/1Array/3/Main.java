public class Main
{
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    public static void bubbleSort(int[] array) {
        boolean swapped;
        for (int i = 0; i < array.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static int[] randomizeArray(int[] array) {
        for (int i = 0 ; i < array.length ;i +=1){
            array[i] = (int)(Math.random()*100);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = randomizeArray( new int[100] ); 

        selectionSort(array);
        System.out.println("selection sort: ");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
        bubbleSort(array);
        System.out.println("bubble sort: ");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
