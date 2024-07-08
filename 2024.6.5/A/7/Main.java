public class Main {
    public static void main(String[] args) {
        Musician musician = new Musician();

        Instrument erhu = new Erhu();
        Instrument piano = new Piano();
        Instrument violin = new Violin();

        musician.play(erhu);    
        musician.play(piano);   
        musician.play(violin);  
    }
}

abstract class Instrument {
    public abstract void makeSound();
}

class Erhu extends Instrument {
    @Override
    public void makeSound() {
        System.out.println("二胡奏乐");
    }
}

class Piano extends Instrument {
    @Override
    public void makeSound() {
        System.out.println("钢琴奏乐");
    }
}

class Violin extends Instrument {
    @Override
    public void makeSound() {
        System.out.println("小提奏乐");
    }
}
class Musician {
    public void play(Instrument i) {
        i.makeSound();
    }
}
