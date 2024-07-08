public class Main {
    public static void main(String[] args) {
        Geo[] geos = new Geo[2];

        geos[0] = new Sph(5); 
        geos[1] = new Cub(3, 4, 5); 

        for (Geo geo: geos) {
            System.out.println("Area: " + geo.area());
            System.out.println("Volume: " + geo.volume());
            System.out.println();
        }
    }
}

abstract class Geo {
    abstract double area();
    abstract double volume();
}

class Sph extends Geo {
    private double radius;

    public Sph(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }

    @Override
    double volume() {
        return (4.0 / 3) * Math.PI * Math.pow(radius, 3);
    }
}

class Cub extends Geo {
    private double length;
    private double width;
    private double height;

    public Cub(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    double area() {
        return 2 * (length * width + width * height + height * length);
    }

    @Override
    double volume() {
        return length * width * height;
    }
}

