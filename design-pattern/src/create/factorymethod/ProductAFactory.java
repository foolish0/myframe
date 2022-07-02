package create.factorymethod;

public class ProductAFactory implements AbstractFactory {

    @Override
    public Product create() {
        return new ProductA();
    }
}
