package ui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class CustomerDashboard extends JFrame {

    private JLabel welcomeLabel;
    private JComboBox<String> categoryCombo;
    private JComboBox<String> subCategoryCombo;
    private JPanel itemPanel;
    private JLabel totalLabel;
    private JButton viewCartButton;
    private int totalAmount = 0;
    private HashMap<String, Integer> cartQuantities = new HashMap<>();

    public CustomerDashboard(String customerName) {
        setTitle("Customer Dashboard");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Top wrapper with welcome and category filters
        JPanel topWrapper = new JPanel(new BorderLayout());

        welcomeLabel = new JLabel("Welcome, " + customerName + "!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(0, 102, 204));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        topWrapper.add(welcomeLabel, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        categoryCombo = new JComboBox<>(new String[]{"-- Select Category --", "Electronics", "Clothing", "Groceries"});
        subCategoryCombo = new JComboBox<>();
        topPanel.add(new JLabel("Category:"));
        topPanel.add(categoryCombo);
        topPanel.add(new JLabel("Sub-Category:"));
        topPanel.add(subCategoryCombo);
        topWrapper.add(topPanel, BorderLayout.SOUTH);

        add(topWrapper, BorderLayout.NORTH);

        // Center Panel with GridLayout for 2-column scrollable item cards
        itemPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(itemPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel for total and view cart
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        totalLabel = new JLabel("Total: ₹0");
        viewCartButton = new JButton("View Cart");
        bottomPanel.add(totalLabel);
        bottomPanel.add(viewCartButton);
        add(bottomPanel, BorderLayout.SOUTH);

        loadSampleItems();
    }

    private void loadSampleItems() {
        for (int i = 1; i <= 20; i++) {
            String itemName = "Item " + i;
            int itemPrice = i * 100;

            JPanel card = new JPanel(new GridLayout(4, 1));
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            JLabel name = new JLabel(itemName, JLabel.CENTER);
            JLabel price = new JLabel("Price: ₹" + itemPrice, JLabel.CENTER);

            JComboBox<Integer> quantityCombo = new JComboBox<>();
            for (int q = 0; q <= 10; q++) quantityCombo.addItem(q);

            JButton addToCart = new JButton("Add to Cart");

            addToCart.addActionListener(e -> {
                int newQuantity = (int) quantityCombo.getSelectedItem();
                int oldQuantity = cartQuantities.getOrDefault(itemName, 0);

                int delta = newQuantity - oldQuantity;
                totalAmount += delta * itemPrice;
                totalLabel.setText("Total: ₹" + totalAmount);

                cartQuantities.put(itemName, newQuantity);
            });

            card.add(name);
            card.add(price);
            card.add(quantityCombo);
            card.add(addToCart);

            itemPanel.add(card);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CustomerDashboard("Customer").setVisible(true));
    }
}
