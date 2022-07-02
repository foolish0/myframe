package create.simplefactory;

public class Factory {
    public Product create(String name) {
        switch (name) {
            case "A":
                return new ProductA();
            case "B":
                return new ProductB();
            default:
                throw new RuntimeException("No such product!");
        }
    }
}
