package ui;

import dao.OrderDAO;
import model.Order;
import model.OrderItem;
import service.ProductService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutPage extends JFrame {

    private JTable cartTable;
    private DefaultTableModel tableModel;
    private JLabel totalLabel;
    private JButton checkoutButton;
    private JRadioButton cashButton, onlineButton;
    private ButtonGroup paymentGroup;

    private HashMap<String, Integer> cartItems;
    private HashMap<String, Integer> itemPrices;

    public CheckoutPage(HashMap<String, Integer> cartItems, HashMap<String, Integer> itemPrices) {
        this.cartItems = cartItems;
        this.itemPrices = itemPrices;

        setTitle("Checkout");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Cart Table
        tableModel = new DefaultTableModel(new Object[]{"Item", "Quantity", "Unit Price", "Subtotal"}, 0);
        cartTable = new JTable(tableModel);
        loadCartData();
        add(new JScrollPane(cartTable), BorderLayout.CENTER);

        // Payment Options
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        JPanel paymentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        paymentPanel.setBorder(BorderFactory.createTitledBorder("Select Payment Method"));
        cashButton = new JRadioButton("Cash on Delivery");
        onlineButton = new JRadioButton("Online Payment");
        cashButton.setSelected(true);
        paymentGroup = new ButtonGroup();
        paymentGroup.add(cashButton);
        paymentGroup.add(onlineButton);
        paymentPanel.add(cashButton);
        paymentPanel.add(onlineButton);

        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalLabel = new JLabel("Total: ₹" + calculateTotal());
        checkoutButton = new JButton("Checkout");

        checkoutButton.addActionListener(e -> placeOrder());

        totalPanel.add(totalLabel);
        totalPanel.add(checkoutButton);

        bottomPanel.add(paymentPanel);
        bottomPanel.add(totalPanel);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void placeOrder() {
        String paymentType = getSelectedPaymentType();
        if (paymentType == null) {
            JOptionPane.showMessageDialog(this, "Please select a payment type.");
            return;
        }

        Order order = new Order();
        order.setUserId(getLoggedInUserId());
        order.setTotalAmount(calculateTotal());
        order.setPaymentType(paymentType);
        order.setStatus("pending");

        List<OrderItem> items = new ArrayList<>();
        ProductService productService = new ProductService();

        for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
            String name = entry.getKey();
            int qty = entry.getValue();
            int price = itemPrices.get(name);
            int productId = productService.getProductIdByName(name);

            if (productId == -1) {
                JOptionPane.showMessageDialog(this, "Product not found in database: " + name);
                return;
            }

            OrderItem item = new OrderItem();
            item.setProductId(productId);
            item.setQuantity(qty);
            item.setPrice(price);
            items.add(item);
        }

        order.setItems(items);

        try {
            OrderDAO dao = new OrderDAO();
            int orderId = dao.placeOrder(order);
            JOptionPane.showMessageDialog(this, "Order placed successfully! Order ID: " + orderId);
            this.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to place order: " + ex.getMessage());
        }
    }

    private String getSelectedPaymentType() {
        if (cashButton.isSelected()) return "cash";
        if (onlineButton.isSelected()) return "online";
        return null;
    }

    private int getLoggedInUserId() {
        return 2; // Hardcoded for now, replace with actual login session
    }

    private void loadCartData() {
        tableModel.setRowCount(0);
        for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
            String item = entry.getKey();
            int qty = entry.getValue();
            int price = itemPrices.getOrDefault(item, 0);
            int subtotal = qty * price;
            tableModel.addRow(new Object[]{item, qty, "₹" + price, "₹" + subtotal});
        }
    }

    private int calculateTotal() {
        int total = 0;
        for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
            total += entry.getValue() * itemPrices.get(entry.getKey());
        }
        return total;
    }
}
