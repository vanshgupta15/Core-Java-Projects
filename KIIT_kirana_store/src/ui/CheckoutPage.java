package ui;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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

        // 🧾 Table of cart items
        tableModel = new DefaultTableModel(new Object[]{"Item", "Quantity", "Unit Price", "Subtotal"}, 0);
        cartTable = new JTable(tableModel);
        loadCartData();
        JScrollPane scrollPane = new JScrollPane(cartTable);
        add(scrollPane, BorderLayout.CENTER);

        // 🔽 Bottom Panel: Payment + Total + Checkout
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        // 🧾 Payment method section
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

        // ✅ Total + Checkout button panel
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalLabel = new JLabel("Total: ₹" + calculateTotal());
        checkoutButton = new JButton("Checkout");

        checkoutButton.addActionListener(e -> {
            String paymentType = cashButton.isSelected() ? "cash" : "online";
            JOptionPane.showMessageDialog(this, "Order placed with payment: " + paymentType);
            this.dispose(); // Close window after confirmation
        });

        totalPanel.add(totalLabel);
        totalPanel.add(checkoutButton);

        // Add both panels to bottomPanel
        bottomPanel.add(paymentPanel);
        bottomPanel.add(totalPanel);

        // Add bottomPanel to frame
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
