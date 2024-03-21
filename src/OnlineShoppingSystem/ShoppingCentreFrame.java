package OnlineShoppingSystem;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class ShoppingCentreFrame extends JFrame{

    private ArrayList<Product> productList;
    private JButton shoppingCartButton;
    private JComboBox shoppingComboBox;
    private JTable shoppingTable;
    private DefaultTableModel shoppingTableModel;
    private JTextArea detailLabel;
    private JButton addToCartButton;

    private User user;

    public ShoppingCentreFrame(ArrayList<Product> productList, User user) {

        this.productList = productList;
        this.user = user;

        JPanel shoppingPanelUp = new JPanel();
        shoppingPanelUp.setLayout(new BorderLayout());
        shoppingPanelUp.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));

        JPanel shoppingPanelUp1 = new JPanel();
        shoppingPanelUp1.setLayout(new BorderLayout());

        JPanel shoppingPanelUp1Left = new JPanel();
        shoppingPanelUp1Left.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel shoppingLabel = new JLabel("Select Product Category");
        shoppingLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        shoppingLabel.setPreferredSize(new Dimension(140, 25));

        String[] shoppingCategory = {"All", "Electronics", "Clothing"};             // GUI dropdown menu.
        shoppingComboBox = new JComboBox(shoppingCategory);
        shoppingComboBox.setFont(new Font("Arial", Font.PLAIN, 12));
        shoppingComboBox.setPreferredSize(new Dimension(150, 25));

        ShoppingCategoryEvents shoppingCategoryEvents = new ShoppingCategoryEvents();   // Creating an object for the dropdown menu action class.
        shoppingComboBox.addActionListener(shoppingCategoryEvents);

        shoppingPanelUp1Left.add(shoppingLabel);
        shoppingPanelUp1Left.add(shoppingComboBox);

        shoppingPanelUp1Left.setBorder(BorderFactory.createEmptyBorder(20, 50, 40, 0));


        JPanel shoppingPanelUp1Right = new JPanel();
        shoppingPanelUp1Right.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 10));

        shoppingCartButton = new JButton("Shopping Cart");                          // GUI shopping cart button.
        shoppingCartButton.setFont(new Font("Arial", Font.PLAIN, 12));
        shoppingCartButton.setPreferredSize(new Dimension(130, 30));

        shoppingPanelUp1Right.add(shoppingCartButton);

        ShoppingCartButtonEvent shoppingCartButtonEvent = new ShoppingCartButtonEvent();   // Creating an object for the shopping cart button action class.
        shoppingCartButton.addActionListener(shoppingCartButtonEvent);

        shoppingPanelUp1.add(shoppingPanelUp1Left, BorderLayout.WEST);
        shoppingPanelUp1.add(shoppingPanelUp1Right, BorderLayout.EAST);

        shoppingPanelUp.add(shoppingPanelUp1, BorderLayout.NORTH);

        JPanel shoppingPanelUp2 = new JPanel();                                 // GUI product table
        shoppingPanelUp2.setLayout(new BorderLayout());

        String[] columnNames = {"Product ID", "Name", "Category", "Price", "Info"};

        shoppingTable = new JTable();
        shoppingTableModel = new DefaultTableModel(columnNames, 0);

        shoppingTable.setDefaultEditor(Object.class, null);  // Disable editing access of the table.
        shoppingTable.setRowSelectionAllowed(true);


        Collections.sort(productList);                  // Sort the product list.

        // Add product list data to the table.
        for (Product product : productList) {
            shoppingTableModel.addRow(new Object[]{product.getProductId(), product.getProductName(), product.getClass().getSimpleName()
                    , product.getProductPrice(), product.getInfo()});

        }

        shoppingTable.setModel(shoppingTableModel);

        JScrollPane shoppingTableScrollPane = new JScrollPane(shoppingTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();               // Center the table row and colum data to center.
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        shoppingTable.setDefaultRenderer(Object.class, centerRenderer);

        shoppingTable.setRowHeight(30);
        shoppingTable.getColumnModel().getColumn(4).setPreferredWidth(200);
        shoppingTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        shoppingTable.setFont(new Font("Arial", Font.PLAIN, 12));
        shoppingTableScrollPane.setPreferredSize(new Dimension(750, 170));

        DetailLabelEvent detailLabelEvent = new DetailLabelEvent();
        shoppingTable.getSelectionModel().addListSelectionListener(detailLabelEvent);            // Creating an object for the data show in the text area action class.

        ShoppingTableRenderer shoppingTableRenderer = new ShoppingTableRenderer();          // Apply a colour to the row
        for (int i = 0; i < columnNames.length; i++) {
            shoppingTable.getColumnModel().getColumn(i).setCellRenderer(shoppingTableRenderer);
        }

        shoppingPanelUp2.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        shoppingPanelUp2.add(shoppingTableScrollPane, BorderLayout.CENTER);

        shoppingPanelUp.add(shoppingPanelUp2, BorderLayout.CENTER);


        JPanel shoppingPanelDown = new JPanel();
        shoppingPanelDown.setLayout(new BorderLayout());

        JPanel shoppingPanelDown1 = new JPanel();
        shoppingPanelDown1.setLayout(new FlowLayout(FlowLayout.LEFT, 70, 40));

        detailLabel = new JTextArea();                                          // GUI text area to show the table selected product data.
        detailLabel.setFont(new Font("Arial", Font.BOLD, 13));
        detailLabel.setPreferredSize(new Dimension(250, 210));
        detailLabel.setBackground(new Color(238,238,238));
        detailLabel.setEditable(false);

        shoppingPanelDown1.add(detailLabel);

        JPanel shoppingPanelDown2 = new JPanel();
        shoppingPanelDown2.setLayout(new FlowLayout());

        addToCartButton = new JButton("Add to Shopping Cart");              // GUI button for add product to the cart.
        addToCartButton.setFont(new Font("Arial", Font.PLAIN, 12));
        addToCartButton.setPreferredSize(new Dimension(160, 25));
        addToCartButton.setEnabled(false);

        AddToShoppingCartEvent addToShoppingCartEvent = new AddToShoppingCartEvent();  // Creating an object for the add product to cart action class.
        addToCartButton.addActionListener(addToShoppingCartEvent);

        shoppingPanelDown2.add(addToCartButton);

        shoppingPanelDown.add(shoppingPanelDown1, BorderLayout.NORTH);
        shoppingPanelDown.add(shoppingPanelDown2, BorderLayout.CENTER);

        this.add(shoppingPanelUp, BorderLayout.NORTH);
        this.add(shoppingPanelDown, BorderLayout.SOUTH);

    }

    private class ShoppingCategoryEvents implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedCategory = (String) shoppingComboBox.getSelectedItem();
            shoppingTableModel.setRowCount(0); // reset the table

            if ("Electronics".equals(selectedCategory)) {
                for (Product product : productList) {
                    if (product instanceof Electronics) {
                        shoppingTableModel.addRow(new Object[]{product.getProductId(), product.getProductName(), product.getClass().getSimpleName()
                                , product.getProductPrice(), product.getInfo()});
                    }
                }
            }else if ("Clothing".equals(selectedCategory)) {
                for (Product product : productList) {
                    if (product instanceof Clothing)
                        shoppingTableModel.addRow(new Object[]{product.getProductId(), product.getProductName(), product.getClass().getSimpleName()
                                , product.getProductPrice(), product.getInfo()});
                }
            } else {
                for (Product product : productList) {
                    shoppingTableModel.addRow(new Object[]{product.getProductId(), product.getProductName(), product.getClass().getSimpleName()
                            , product.getProductPrice(), product.getInfo()});
                }
            }

        }
    }

    private class DetailLabelEvent implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = shoppingTable.getSelectedRow();
                if (selectedRow != -1) {
                    Object[] selectedRowData = shoppingTableModel.getDataVector().elementAt(selectedRow).toArray();

                    for (Product product : productList) {
                        if (product.getProductId().equals(String.valueOf(selectedRowData[0]))) {
                            if (product instanceof Electronics) {
                                detailLabel.setText("Select Product - Details\n\n" +
                                        "Product Id: " + product.getProductId() + "\n\n" +
                                        "Category: " + product.getClass().getSimpleName() + "\n\n" +
                                        "Name: " + ((Electronics) product).getBrand() + " " + product.getProductName() + "\n\n" +
                                        "Warranty period: " + ((Electronics) product).getWarrantyPeriod() + "\n\n" +
                                        "Brand: " + ((Electronics) product).getBrand() + "\n\n" +
                                        "Items Available: " + product.getNumberOfAvailableItems());
                            } else {
                                detailLabel.setText("Select Product - Details\n\n" +
                                        "Product Id: " + product.getProductId() + "\n\n" +
                                        "Category: " + product.getClass().getSimpleName() + "\n\n" +
                                        "Name: " + ((Clothing) product).getColour() + " " + product.getProductName() + "\n\n" +
                                        "Size: " + ((Clothing) product).getSize() + "\n\n" +
                                        "Colour: " + ((Clothing) product).getColour() + "\n\n" +
                                        "Items Available: " + product.getNumberOfAvailableItems());
                            }
                            addToCartButton.setEnabled(true);
                        }
                    }
                }
            }
        }
    }
    private class AddToShoppingCartEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = shoppingTable.getSelectedRow();
            if (selectedRow != -1) {
                Object[] selectedTableRow = shoppingTableModel.getDataVector().elementAt(selectedRow).toArray();
                for (Product product : productList) {
                    if (product.getProductId().equals(String.valueOf(selectedTableRow[0]))) {

                        boolean productAlreadyNotInCart = true;
                        for (Product sCartProduct : user.getShoppingCart().getCartList()) {
                            if (sCartProduct.getProductId().equals(product.getProductId())) {
                                int updatedQuantity = sCartProduct.getProductQuantity() + 1;

                                if (updatedQuantity <= sCartProduct.getNumberOfAvailableItems()) {
                                    sCartProduct.setProductQuantity(updatedQuantity);
                                    product.setNumberOfAvailableItems(product.getNumberOfAvailableItems() - 1);
                                }
                                else {
                                    System.out.println("Can not add " + updatedQuantity + " number of items to the cart at once.");
                                }
                                productAlreadyNotInCart = false;
                                break;
                            }

                        }

                        if (productAlreadyNotInCart) {
                            Product newSelectedProduct = getProduct(product);
                            user.getShoppingCart().addProduct(newSelectedProduct);
                        }
                    }
                }
            }
        }

        private static Product getProduct(Product product) {
            Product newSelectedProduct;
            if (product instanceof Electronics) {
                Electronics electronicItem = (Electronics) product;
                String productId = electronicItem.getProductId();
                String productName = electronicItem.getProductName();
                int numberOfAvailableItems = electronicItem.getNumberOfAvailableItems();
                double productPrice = electronicItem.getProductPrice();
                String brand = electronicItem.getBrand();
                int warrantyPeriod = electronicItem.getWarrantyPeriod();

                newSelectedProduct =
                        new Electronics(productId, productName, numberOfAvailableItems, productPrice, brand, warrantyPeriod);
            }
            else {
                Clothing clothingItem = (Clothing) product;
                String productId = clothingItem.getProductId();
                String productName = clothingItem.getProductName();
                int numberOfAvailableItems = clothingItem.getNumberOfAvailableItems();
                double productPrice = clothingItem.getProductPrice();
                String size = clothingItem.getSize();
                String colour = clothingItem.getColour();

                newSelectedProduct =
                        new Clothing(productId, productName, numberOfAvailableItems, productPrice, size, colour);
            }
            newSelectedProduct.setProductQuantity(1);
            product.setNumberOfAvailableItems(product.getNumberOfAvailableItems() - 1);
            return newSelectedProduct;
        }
    }
    private class ShoppingCartButtonEvent implements ActionListener {
        ShoppingCartFrame cartFrame;   //check this again
        public void actionPerformed(ActionEvent e) {
            if (user.isVeryFirstPurchase()) {
                user.setVeryFirstPurchase(false);
                if (e.getActionCommand().equals("Shopping Cart") && (cartFrame == null || !cartFrame.isVisible())) {
                    cartFrame = new ShoppingCartFrame(user.getShoppingCart(), true);
                    cartFrame.setTitle("Shopping Cart");
                    cartFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    cartFrame.setSize(700, 460);
                    cartFrame.setAlwaysOnTop(true);
                    cartFrame.setLocationRelativeTo(null);
                    cartFrame.setVisible(true);
                }
            }
            else {
                if (e.getActionCommand().equals("Shopping Cart") && (cartFrame == null || !cartFrame.isVisible())) {
                    cartFrame = new ShoppingCartFrame(user.getShoppingCart(), false);
                    cartFrame.setTitle("Shopping Cart");
                    cartFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    cartFrame.setSize(700, 460);
                    cartFrame.setAlwaysOnTop(true);
                    cartFrame.setLocationRelativeTo(null);
                    cartFrame.setVisible(true);
                }
            }
        }
    }

    private class ShoppingTableRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            Object cellValue = table.getValueAt(row, 0);
            for (Product product : productList) {
                if (cellValue.equals(product.getProductId())) {
                    int availableItemCount = product.getNumberOfAvailableItems();
                    if (availableItemCount < 3 && availableItemCount >= 0) {
                        cellComponent.setBackground(Color.RED.brighter());
                    }
                    else {
                        cellComponent.setBackground(table.getBackground());
                    }
                }
            }

            return cellComponent;
        }
    }
}
