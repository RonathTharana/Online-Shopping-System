package OnlineShoppingSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ShoppingCartFrame extends JFrame {

    private ShoppingCart userCart;
    private boolean veryFirstPurchase;

    public ShoppingCartFrame(ShoppingCart userCart , boolean veryFirstPurchase) {
        this.userCart = userCart;
        this.veryFirstPurchase = veryFirstPurchase;

        JPanel shoppingCartPanelUp = new JPanel();
        shoppingCartPanelUp.setLayout(new BorderLayout());

        String[] columnNames = {"Product", "Quantity", "Price"};

        JTable shoppingCartTable = new JTable();
        DefaultTableModel shoppingCartTableModel = new DefaultTableModel(columnNames, 0);
        shoppingCartTable.setModel(shoppingCartTableModel);

        for (Product product : userCart.getCartList()) {
            if (product instanceof Electronics) {
            shoppingCartTableModel.addRow(new Object[]{(product.getProductId() + ", "
                    + product.getProductName() + ", "
                    + ((Electronics) product).getWarrantyPeriod() + ", " + ((Electronics) product).getBrand())
                    , product.getProductQuantity(), (product.getProductPrice() * product.getProductQuantity()) + " £"});
            }
            else {
                shoppingCartTableModel.addRow(new Object[]{(product.getProductId() + ", "
                        + product.getProductName() + ", "
                        + ((Clothing) product).getSize() + ", " + ((Clothing) product).getColour())
                        , product.getProductQuantity(), (product.getProductPrice() * product.getProductQuantity()) + " £"});
            }
        }

        JScrollPane shoppingCartTableScrollPane = new JScrollPane(shoppingCartTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();  // CENTER THE TABLE CONTENT
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        shoppingCartTable.setDefaultRenderer(Object.class, centerRenderer);

        shoppingCartTable.setRowHeight(60);
        shoppingCartTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        shoppingCartTable.setFont(new Font("Arial", Font.PLAIN, 12));
        shoppingCartTableScrollPane.setPreferredSize(new Dimension(600, 140));

        shoppingCartPanelUp.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        shoppingCartPanelUp.add(shoppingCartTableScrollPane, BorderLayout.CENTER);


        JPanel shoppingCartPanelDown = new JPanel();
        shoppingCartPanelDown.setLayout(new FlowLayout(FlowLayout.RIGHT, 50, 20));

        JTextArea priceLabel = new JTextArea();
        priceLabel.setText(cartDetails());
        priceLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        priceLabel.setEditable(false);
        priceLabel.setBackground(new Color(238,238,238));
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 13).deriveFont(Font.BOLD));
        priceLabel.setPreferredSize(new Dimension(400, 200));
        priceLabel.setBounds(170, 180, 400, 200);

        shoppingCartPanelDown.add(priceLabel);

        this.add(shoppingCartPanelUp, BorderLayout.NORTH);
        this.add(shoppingCartPanelDown, BorderLayout.SOUTH);

    }
    private double finalCost() {
        double discountFirst = veryFirstPurchaseDiscount(veryFirstPurchase);
        double discountThree = sameCategoryDiscount();
        double totalCost = userCart.totalCost();
        return (totalCost - discountFirst - discountThree);
    }
    public double veryFirstPurchaseDiscount(boolean veryFirstPurchase) {
        if (veryFirstPurchase) {
            return userCart.totalCost() * 0.1;
        }else {
            return 0;
        }
    }
    public double sameCategoryDiscount() {
        for (Product product : userCart.getCartList()) {
            if (product.getProductQuantity() >= 3) {
                return userCart.totalCost() * 0.2;
            }
        }
        return 0;
    }
    private String cartDetails() {
        return " £ Total      " + userCart.totalCost() + "\n\n"
                        + " £ First Purchase Discount (10%)        " + "- "
                        + Math.round(veryFirstPurchaseDiscount(veryFirstPurchase) * 100.0) / 100.0 + " \n\n"
                        + " £ Three Items in Same Category Discount (20%)      " + "- "
                        + Math.round(sameCategoryDiscount() * 100.0) / 100.0 + "\n\n\n"
                        + " £ Final Total      " + Math.round(finalCost()) * 100.0 / 100.0;
    }

}
