package OnlineShoppingSystem;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {

    private ArrayList<Product> productList;

    // Maximum number of products can be in the system.
    private static final int numberOfProducts = 50;

    // Initializing scanner class object.
    private Scanner getInput = new Scanner(System.in);

    private ShoppingCentreFrame shoppingFrame;

    // Initializing user class object.
    private User user = new User("Admin", "1234");

    // Westminster shopping manager class constructor.
    public WestminsterShoppingManager() {
        this.productList = new ArrayList<>();
    }

    /**
     * Method to run the manager console menu.
     * @return the user requirement that system want to run or not.
     */
    @Override
    public boolean runMenu() {

        String line = "=====";
        String blank = "     ";
        boolean start = true;
        System.out.println("\n" + line.repeat(20));
        System.out.println(blank.repeat(6) + " Welcome to Westminster Shopping Center");

        // Creating the menu options and printing the menu options.

        while (start) {
            System.out.println("\n" + line.repeat(20));
            System.out.println("Select an option from the menu below:");
            System.out.println("1. Add a new product");
            System.out.println("2. Delete a product");
            System.out.println("3. Print the list of products");
            System.out.println("4. Save in a file");
            System.out.println("5. GUI");
            System.out.println("6. Exit");
            System.out.println(line.repeat(20));

            int option = readInteger("Enter your option:");

            switch (option) {
                case 1:
                    addNewProduct();
                    break;
                case 2:
                    deleteProductFromList();
                    break;
                case 3:
                    orderedProductList();
                    break;
                case 4:
                    saveProductListToFile();
                    break;
                case 5:
                    graphicalUserInterface();
                    break;
                case 6:
                    start = false;
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        return start;
    }

    /**
     * Method to select what type of product category want to add to the system.
     */
    @Override
    public void addNewProduct() {

        System.out.println("Select the product type");
        System.out.println("1. Electronic");
        System.out.println("2. Clothing\n");

        int typeNumber = readInteger("Enter the type:");

        switch (typeNumber) {
            case 1:
                addNewElectronic();
                break;
            case 2:
                addNewClothing();
                break;
            default:
                System.out.println("Invalid product type. Please Try again.");
                addNewProduct();
        }
    }

    /**
     * Method to get input and initialize the electronics class object.
     */
    @Override
    public void addNewElectronic() {

        String productId = readString("Enter the product Id");
        String productNameElectronic = readString("Enter the product name");
        String capitalizeProductName = productNameElectronic.substring(0, 1).toUpperCase() + productNameElectronic.substring(1);  //******************

        int availableItems = readInteger("Enter the number of available items");
        double price = readDouble("Enter the product price");
        String itemBrand = readString("Enter the product brand");
        String capitalizeBrand = itemBrand.substring(0, 1).toUpperCase() + itemBrand.substring(1);

        int warranty = readInteger("Enter the warranty in weeks");

        Product electronicItem = new Electronics(productId, capitalizeProductName, availableItems, price, capitalizeBrand, warranty);

        addProductToList(electronicItem);
    }

    /**
     * Method to get input and initializing the clothing class object.
     */
    @Override
    public void addNewClothing() {

        String productId = readString("Enter the product Id");
        String productNameClothing = readString("Enter the product name");
        String capitalizeProductName = productNameClothing.substring(0, 1).toUpperCase() + productNameClothing.substring(1);

        int availableItems = readInteger("Enter the number of available items");
        double price = readDouble("Enter the product price");
        String size = readString("Enter the product size");
        String capitalizeSize = size.substring(0, 1).toUpperCase() + size.substring(1);

        String colour = readString("Enter the product colour");
        String capitalizeColour = colour.substring(0, 1).toUpperCase() + colour.substring(1);

        Product clothingItem = new Clothing(productId, capitalizeProductName, availableItems, price, capitalizeSize, capitalizeColour);

        addProductToList(clothingItem);
    }

    /**
     * Method to add product object to the product list.
     * @param product the product class object.
     */
    @Override
    public void addProductToList(Product product) {

        if (productList.size() < numberOfProducts) {
            for (Product similarProduct : productList) {                             // check that the product id is already exist in arraylist
                if (similarProduct.getProductId().equals(product.getProductId())) {
                    System.out.println("Product Id already exist in the system.");
                    return;
                }
            }
            productList.add(product);
            System.out.println("Product " + product.getProductId() + " successfully added to the product list.");
        } else {
            System.out.println("Product list is full.");
        }
    }

    /**
     * Method to deleted selected product from the product list.
     */
    @Override
    public void deleteProductFromList() {

        for (Product item : productList) {
            System.out.println("Product Id: " + item.getProductId());
            System.out.println("Product Name: " + item.getProductName());
            System.out.println("Category: " + item.getClass().getSimpleName() + "\n");
        }

        String productId = readString("Enter the product Id");

        if (productList.isEmpty()) {
            System.out.println("Product list is Empty.");
        } else {
            for (Product product : productList) {
                if (product.getProductId().equals(productId)) {
                    System.out.println("Product category: " + product.getClass().getSimpleName()); // CHECK IF OTHER PRODUCT INFO IS NEEDED
                    System.out.println("Product " + product.getProductId() +" successfully delete from the product list.");
                    productList.remove(product);
                    System.out.println("Number of products left in the product list: " + productList.size());
                    return;
                }
            }
            System.out.println("No product found in the product list that match to the product Id.");
        }
    }

    /**
     * Method to sort the product list by product id and display the product details.
     */
    @Override
    public void orderedProductList() {

        if (productList.isEmpty()) {
            System.out.println("Product list is Empty.");
        } else {
            Collections.sort(productList);
            System.out.println("Product list sorted successfully.\n");
            for (Product product : productList) {
                System.out.println(product.toString());
                System.out.println("Category: " + product.getClass().getSimpleName() + "\n");
            }
            System.out.println("Product list displayed successfully.");
        }
    }

    /**
     * Method to save product list data to a file.
     */
    //https://samderlust.com/dev-blog/java/write-read-arraylist-object-file-java
    @Override
    public void saveProductListToFile() {

        if (productList.isEmpty()) {
            System.out.println("Product list is Empty.\nDo not have any product data to save.");
        } else {
            try {
                File productFile = new File("productList.txt");

                if (productFile.createNewFile()) {
                    System.out.println("File created : " + productFile.getName());
                    System.out.println("Path : " + productFile.getAbsolutePath());
                } else {
                    System.out.println("File exist : " + productFile.getName());
                    System.out.println("Path : " + productFile.getAbsolutePath());
                }

                FileOutputStream writeProductList = new FileOutputStream(productFile);
                ObjectOutputStream writeProductListStream = new ObjectOutputStream(writeProductList);

                for (Product product : productList) {
                    writeProductListStream.writeObject(product);
                }

                writeProductListStream.flush();
                writeProductListStream.close();
                writeProductList.close();

                System.out.println("\nAll the details have been saved into the file successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred. \nPlease try again.");
                e.fillInStackTrace();
            }
        }
    }

    /**
     * Method to read the saved data from the file and save to the product list.
     * @throws IOException throws the error to the main menu.
     */
    //https://samderlust.com/dev-blog/java/write-read-arraylist-object-file-java
    @Override
    public void loadProductListFromFile() throws IOException {

        FileInputStream readProductList = new FileInputStream("productList.txt");
        ObjectInputStream readProductListStream = new ObjectInputStream(readProductList);

        for (int i = 0; i < 10000; i++) {
            try {
                Product product = (Product) readProductListStream.readObject();
                productList.add(product);
            } catch (EOFException | ClassNotFoundException e) {
                break;
            }
        }
        readProductListStream.close();
        readProductList.close();

    }

    /**
     * Method to initialize the GUI frame object.
     */
    public void graphicalUserInterface() {

        if (shoppingFrame == null || !shoppingFrame.isVisible()) {   //check this again
            shoppingFrame = new ShoppingCentreFrame(productList, user);
            shoppingFrame.setTitle("Westminster Shopping Center");
            shoppingFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            shoppingFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    user.getShoppingCart().removeProduct();
                }
            });
            shoppingFrame.setSize(800, 660);
            shoppingFrame.setAlwaysOnTop(true);
            shoppingFrame.setLocationRelativeTo(null);
            shoppingFrame.setVisible(true);
        }

    }

    /**
     * Reading a string using scanner.
     * @param massage the statement that ask for an input.
     * @return the selected string type input.
     */
    @Override
    public String readString(String massage) {

        System.out.println(massage);
        try {
            String input = getInput.next();
            getInput.nextLine();
            return input;

        } catch (Exception e) {
            getInput.nextLine();
            System.out.println("Invalid response. Please try again.");
            return readString(massage);
        }
    }

    /**
     * Reading an integer using scanner.
     * @param massage the statement that ask for an input.
     * @return the selected integer type input.
     */
    @Override
    public int readInteger(String massage) {

        System.out.println(massage);
        try {
            int input = getInput.nextInt();
            getInput.nextLine();
            return input;

        } catch (Exception e) {
            getInput.nextLine();
            System.out.println("Invalid response. Please try again.");
            return readInteger(massage);
        }
    }

    /**
     * Reading a double using scanner.
     * @param massage the statement that ask for an input.
     * @return the selected double type input.
     */
    @Override
    public double readDouble(String massage) {

        System.out.println(massage);
        try {
            double input = getInput.nextDouble();
            getInput.nextLine();
            return input;

        } catch (Exception e) {
            getInput.nextLine();
            System.out.println("Invalid response. Please try again.");
            return readDouble(massage);
        }
    }
}
