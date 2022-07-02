package create.simplefactory;

public class Factory {
    public static Product create(String name) {
        switch (name) {
            case "A":
                return new ProductA(name);
            case "B":
                return new ProductB(name);
            default:
                throw new RuntimeException("No such product!");
        }
    }
}
