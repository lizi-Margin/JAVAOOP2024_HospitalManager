public class Main {
    public static void main(String[] args) {
        P parent = new P();
        parent.test(); 

        C child = new C();
        child.test(); 

        P parentRefToChild = new C();
        parentRefToChild.test(); 
    }
}

class P {
    public void test() {
        System.out.println("Parent test");
    }
}

class C extends P {
    @Override
    public void test() {
        System.out.println("Child test");
    }
}

