package OnlineShoppingSystem;

public class Electronics extends Product{
    private String brand;
    private int warrantyPeriod;

    // Electronic class default constructor.
    public Electronics() {
        super();
        this.brand = "brand";
        this.warrantyPeriod = 0;
    }

    public Electronics(String brand, int warrantyPeriod) {
        super();
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    // Electronics class constructor.
    public Electronics(String productId, String productName, int numberOfAvailableItems, double productPrice, String brand, int warrantyPeriod) {
        super(productId, productName, numberOfAvailableItems, productPrice);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    // Override the getInfo abstract method.
    @Override
    public String getInfo() {
        return brand + ", " + warrantyPeriod + " weeks warranty";
    }

    @Override
    public String toString() {
        return super.toString() +
                "Brand: " + brand + "\n" +
                "Warranty Period: " + warrantyPeriod;
    }
}
