
interface Relatives {

    void print2();

    default void print3() {

        System.out.println("Hello from DEFAULT");

    }

}

class Parent implements Relatives{

    public static void print(){
        System.out.println("Hello from Parent!!!");
    }

    @Override
    public void print2() {

    }
}

class Child extends Parent {

    static {

    }

    public static void print(){
        System.out.println("Hello from Child!!!");
    }

    @Override
    public void print3() {
        System.out.println("Hello from DEFAULT of CHILD");
    }
}

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");

        Parent p1 = new Parent();
        p1.print();

        Child c1 = new Child();
        c1.print();

        Parent p2 = c1 ;
        p2.print();
        p2.print3();

    }

}
