package create.factorymethod;

public class ProductB implements Product {

    @Override
    public void printName() {
        System.out.println(this.getClass().getName() + "-B");
    }
}
