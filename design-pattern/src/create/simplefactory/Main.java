package create.simplefactory;

public class Main {
    public static void main(String[] args) {
        Product a = new Factory().create("A");
        a.printName();
    }
}
