package OnlineShoppingSystem;

import java.io.IOException;

//need to check again
public class Main {

    public static void main(String[] args) {

        // Initializing westminster shopping manager class object.
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Reading the save data from the file.
        try {
            shoppingManager.loadProductListFromFile();
        }
        catch (IOException e) {
            e.fillInStackTrace();
        }

        boolean start = true;
        while (start) {
            start = shoppingManager.runMenu();
        }
    }

}

