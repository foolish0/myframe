package create.factorymethod;

public class ProductA implements Product {

    @Override
    public void printName() {
        System.out.println(this.getClass().getName() + "-A");
    }
}
