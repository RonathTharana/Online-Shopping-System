package OnlineShoppingSystem;

import java.util.ArrayList;

public class ShoppingCart {

    private ArrayList<Product> productCart;

    public ShoppingCart() {
        this.productCart = new ArrayList<>();
    }

    // Add a product class object to the arraylist.
    public void addProduct(Product product) {

        this.productCart.add(product);
    }

    // Remove product class objects from the arraylist.
    public void removeProduct() {
        this.productCart.clear();
    }

    public ArrayList<Product> getCartList() {
        return productCart;
    }

    // Calculating shopping cart total cost.
    public double totalCost() {
        double calculateTotalCost = 0.0;
        for (Product product : productCart) {
            calculateTotalCost += product.getProductPrice() * product.getProductQuantity();
        }
        return calculateTotalCost;
    }
}
