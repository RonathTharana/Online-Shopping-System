package OnlineShoppingSystem;

public class User {
    private String userName;
    private String password;
    private ShoppingCart shoppingCart;
    private boolean veryFirstPurchase;

    // User class default constructor.
    public User() {
        this.userName = "Admin";
        this.password = "Password";
    }

    // User class constructor.
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.shoppingCart = new ShoppingCart();
        this.veryFirstPurchase = true;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    // Check that the user is a new user.
    public boolean isVeryFirstPurchase() {
        return veryFirstPurchase;
    }

    public void setVeryFirstPurchase(boolean veryFirstPurchase) {
        this.veryFirstPurchase = veryFirstPurchase;
    }
}
