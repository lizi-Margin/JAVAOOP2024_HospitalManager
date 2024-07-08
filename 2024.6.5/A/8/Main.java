import java.lang.Comparable;

public class Main{
    public static void main(String[] args) {
        Complex c1 = new Complex(4, 5);
        Complex c2 = new Complex(3, -2);


        if (c1.compareTo(c2) >0) {
            System.out.println(c1 + " > "+ c2);
        } else if (c1.compareTo(c2)<0) {
            System.out.println(c1 + " < " + c2);
        } else {
            System.out.println(c1 + " = " + c2);
        }
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