public class Main {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Array arr = new Array(array);

        // 测试最大值
        System.out.println("Max value: " + arr.getMax());

        // 测试最小值
        System.out.println("Min value: " + arr.getMin());

        // 测试平均值
        System.out.println("Average value: " + arr.getAverage());

        // 测试方差
        System.out.println("Variance: " + arr.getVariance());
    }
}

interface MaxValue {
    int getMax();
}

interface MinValue {
    int getMin();
}

interface AverageValue {
    double getAverage();
}

interface Variance {
    double getVariance();
}


class Array implements MaxValue, MinValue, AverageValue, Variance {
    private int[] array;
    Array(int[] arr){
        array = arr;
    }

    @Override
    public int getMax() {
        int max = array[0];
        for (int i : array) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    @Override
    public int getMin() {
        int min = array[0];
        for (int i : array) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }

    @Override
    public double getAverage() {
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        return (double) sum / array.length;
    }

    @Override
    public double getVariance() {
        double mean = getAverage();
        double sumOfSquares = 0.0;
        for (int i : array) {
            sumOfSquares += Math.pow(i - mean, 2);
        }
        return sumOfSquares / array.length;
    }
}
