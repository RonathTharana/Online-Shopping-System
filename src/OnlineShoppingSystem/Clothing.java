package OnlineShoppingSystem;

public class Clothing extends Product{

    private String size;
    private String colour;

    // Clothing class default constructor.
    public Clothing() {
        super();
        this.size = "small";
        this.colour = "black";
    }

    public Clothing(String size, String colour) {
        super();
        this.size = size;
        this.colour = colour;
    }

    // Clothing class constructor.
    public Clothing(String productId, String productName, int numberOfAvailableItems, double productPrice, String size, String colour) {
        super(productId, productName, numberOfAvailableItems, productPrice);
        this.size = size;
        this.colour = colour;
    }

    public String getSize() {
        return size;
    }

    public String getColour() {
        return colour;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    // Override the getInfo abstract method
    @Override
    public String getInfo() {
        return size + ", " + colour;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Size: " + size + "\n" +
                "Colour: " + colour;
    }
}
