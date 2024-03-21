package OnlineShoppingSystem;

import java.io.IOException;

public interface ShoppingManager {

    boolean runMenu();

    void addNewProduct();

    void addNewElectronic();

    void addNewClothing();

    void addProductToList(Product product);
    void deleteProductFromList();
    void orderedProductList();
    void saveProductListToFile();
    void loadProductListFromFile() throws IOException;

    String readString(String massage);

    int readInteger(String massage);

    double readDouble(String massage);
}
