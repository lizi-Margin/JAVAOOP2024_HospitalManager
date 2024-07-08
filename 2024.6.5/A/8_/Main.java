interface AverageValue<T> {
    T getAverage();
}
class Array<T extends Number> implements AverageValue<T> {
    private T[] array ;
    Array(T[] arr){
        array =  arr;
    }

    @Override
    public T getAverage() {
        if (array[0] instanceof Integer) {
            int sum = 0;
            for (T value : array) {
                sum += value.intValue();
            }
            return (T) Integer.valueOf(sum / array.length);
        } else if (array[0] instanceof Float) {
            float sum = 0;
            for (T value : array) {
                sum += value.floatValue();
            }
            return (T) Float.valueOf(sum / array.length);
        } else if (array[0] instanceof Double) {
            double sum = 0;
            for (T value : array) {
                sum += value.doubleValue();
            }
            return (T) Double.valueOf(sum / array.length);
        } else {
            throw new IllegalArgumentException("Unsupported type");
        }
    }
}
class ComplexArray implements AverageValue<Complex> {

    private Complex[] array ;
    ComplexArray(Complex[] arr){
        array =  arr;
    }
    @Override
    public Complex getAverage() {
        Complex sum = new Complex(0, 0);
        for (Complex value : array) {
            sum = sum.add(value);
        }
        return sum.divide(array.length);
    }
}


public class Main {
    public static void main(String[] args) {
        // 测试整数数组的平均值
        Integer[] intArray = {1, 2, 3, 4, 5};
        Array<Integer> intStats = new Array<>(intArray);
        System.out.println("Average of intArray: " + intStats.getAverage());

        // 测试浮点数数组的平均值
        Float[] floatArray = {1.0f, 2.0f, 3.0f, 4.0f, 5.0f};
        Array<Float> floatStats = new Array<>(floatArray);
        System.out.println("Average of floatArray: " + floatStats.getAverage());

        // 测试双精度浮点数数组的平均值
        Double[] doubleArray = {1.0, 2.0, 3.0, 4.0, 5.0};
        Array<Double> doubleStats = new Array<>(doubleArray);
        System.out.println("Average of doubleArray: " + doubleStats.getAverage());

        // 测试复数数组的平均值
        Complex[] complexArray = {
            new Complex(1, 1),
            new Complex(2, 2),
            new Complex(3, 3),
            new Complex(4, 4),
            new Complex(5, 5)
        };
        ComplexArray complexStats = new ComplexArray(complexArray);
        System.out.println("Average of complexArray: " + complexStats.getAverage());
    }
}

class Complex implements Comparable<Complex> {
    public double real; // 实部
    public double imaginary; // 虚部

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public Complex add(Complex other) {
        double newReal = this.real + other.real;
        double newImaginary = this.imaginary + other.imaginary;
        return new Complex(newReal, newImaginary);
    }

    public Complex subtract(Complex other) {
        double newReal = this.real - other.real;
        double newImaginary = this.imaginary - other.imaginary;
        return new Complex(newReal, newImaginary);
    }

    public Complex multiply(Complex other) {
        double newReal = this.real * other.real - this.imaginary * other.imaginary;
        double newImaginary = this.real * other.imaginary + this.imaginary * other.real;
        return new Complex(newReal, newImaginary);
    }
    public Complex divide(int divisor) {
        return new Complex(this.real / divisor, this.imaginary / divisor);
    }


    public double modulus() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    @Override
    public String toString() {
        if (imaginary >= 0) {
            return real + " + " + imaginary + "i";
        } else {
            return real + " - " + (-imaginary) + "i";
        }
    }

    @Override
    public int compareTo(Complex other) {
        return  Double.compare(this.modulus(), other.modulus());
    }

    
}