package create.factorymethod;

public class Main {
    public static void main(String[] args) {
        AbstractFactory aFactory = new ProductAFactory();
        Product a = aFactory.create();
        a.printName();
    }
}
