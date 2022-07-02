package create.factorymethod;

public class ProductBFactory implements AbstractFactory {

    @Override
    public Product create() {
        return new ProductB();
    }
}
