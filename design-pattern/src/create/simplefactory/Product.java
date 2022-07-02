package create.simplefactory;

public abstract class Product {
    private String name;

    public void printName() {
        System.out.println(this.getClass().getName() + "-" + name);
    }

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
