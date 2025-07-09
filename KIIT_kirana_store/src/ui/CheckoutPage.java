package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CheckoutPage extends JFrame {

    private JTable cartTable;
    private DefaultTableModel tableModel;
    private JLabel totalLabel;
    private JButton checkoutButton;
    private HashMap<String, Integer> cartItems;
    private HashMap<String, Integer> itemPrices;

    public CheckoutPage(HashMap<String, Integer> cartItems, HashMap<String, Integer> itemPrices) {
        this.cartItems = cartItems;
        this.itemPrices = itemPrices;

        setTitle("Checkout");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{"Item", "Quantity", "Unit Price", "Subtotal"}, 0);
        cartTable = new JTable(tableModel);
        loadCartData();

        JScrollPane scrollPane = new JScrollPane(cartTable);
        add(scrollPane, BorderLayout.CENTER);

        totalLabel = new JLabel("Total: ₹" + calculateTotal());
        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Order placed successfully!");
            this.dispose();
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(totalLabel);
        bottomPanel.add(checkoutButton);
        add(bottomPanel, BorderLayout.SOUTH);
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
            total += entry.getValue() * itemPrices.getOrDefault(entry.getKey(), 0);
        }
        return total;
    }
}
