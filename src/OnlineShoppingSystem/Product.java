package OnlineShoppingSystem;

import java.io.Serializable;

public abstract class Product implements Comparable<Product>, Serializable {
    private String productId;
    private String productName;
    private int numberOfAvailableItems;
    private double productPrice;
    private int productQuantity;

    // Product class default constructor.
    protected Product() {
        this.productId = "w1234";
        this.productName = "name";
        this.numberOfAvailableItems = 0;
        this.productPrice = 0.0;
    }

    // Product class constructor.
    protected Product(String productId, String productName, int numberOfAvailableItems, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.numberOfAvailableItems = numberOfAvailableItems;
        this.productPrice = productPrice;
        this.productQuantity = 0;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getNumberOfAvailableItems() {
        return numberOfAvailableItems;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setNumberOfAvailableItems(int numberOfItems) {
        this.numberOfAvailableItems = numberOfItems;
    }

    public void setProductPrice(double price) {
        this.productPrice = price;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productCount) {
        this.productQuantity = productCount;
    }

    // GetInfo abstract method.
    abstract String getInfo();

    @Override
    public String toString() {
        return "Product Id: " + productId + "\n" +
                "Product Name: " + productName + "\n" +
                "Number of available items: " + numberOfAvailableItems + "\n" +
                "Product Price: " + productPrice + "\n";
    }

    // Override the compareTo method to sort the products by product Id.
    //https://www.infoworld.com/article/3323403/java-challengers-5-sorting-with-comparable-and-comparator-in-java.html
    @Override
    public int compareTo(Product product) {      //**********
        return this.productId.compareTo(product.productId);
    }
}
