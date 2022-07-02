package create.simplefactory;

public class Main {
    public static void main(String[] args) {
        Product a = Factory.create("A");
        a.printName();

        Product b = Factory.create("B");
        b.printName();

        Product c = Factory.create("C");
        c.printName();
    }
}
