package ui;

import model.Product;
import service.FormService;
import service.ProductService;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
    private HashMap<String, Integer> itemPrices = new HashMap<>();

    private ArrayList<String> categoryList;

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

        FormService formService = new FormService();
        categoryList = formService.getCategories();

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        categoryCombo = new JComboBox<>();
        subCategoryCombo = new JComboBox<>();

        categoryCombo.addItem("-- Select Category --");
        for (String cat : categoryList) {
            categoryCombo.addItem(cat);
        }

        topPanel.add(new JLabel("Category:"));
        topPanel.add(categoryCombo);
        topPanel.add(new JLabel("Sub-Category:"));
        topPanel.add(subCategoryCombo);
        topWrapper.add(topPanel, BorderLayout.SOUTH);

        add(topWrapper, BorderLayout.NORTH);

        // Scrollable item area
        itemPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(itemPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        totalLabel = new JLabel("Total: ₹0");
        viewCartButton = new JButton("View Cart");
        bottomPanel.add(totalLabel);
        bottomPanel.add(viewCartButton);
        add(bottomPanel, BorderLayout.SOUTH);

        viewCartButton.addActionListener(e -> {
    new CheckoutPage(cartQuantities, itemPrices).setVisible(true);
});


        // Event listeners
        categoryCombo.addActionListener(e -> updateSubCategories());
        subCategoryCombo.addActionListener(e -> loadFilteredProducts());

        // Initially empty
        itemPanel.revalidate();
        itemPanel.repaint();
    }

    private void updateSubCategories() {
        subCategoryCombo.removeAllItems();
        String selectedCategory = (String) categoryCombo.getSelectedItem();
        if (selectedCategory == null || selectedCategory.equals("-- Select Category --")) return;

        FormService formService = new FormService();
        ArrayList<String> subList = formService.getSubCategories(selectedCategory);

        for (String sub : subList) {
            subCategoryCombo.addItem(sub);
        }
    }

    private void loadFilteredProducts() {
        itemPanel.removeAll();

        String category = (String) categoryCombo.getSelectedItem();
        String subCategory = (String) subCategoryCombo.getSelectedItem();

        if (category == null || subCategory == null) return;

        ProductService productService = new ProductService();
        ArrayList<Product> allProducts = (ArrayList<Product>) productService.getAllProducts();

        for (Product p : allProducts) {
            if (p.getCategory().equals(category) && p.getSubCategory().equals(subCategory)) {
                addProductCard(p);
            }
        }

        itemPanel.revalidate();
        itemPanel.repaint();
    }

    private void addProductCard(Product product) {
        JPanel card = new JPanel(new GridLayout(4, 1));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JLabel name = new JLabel(product.getItem(), JLabel.CENTER);
        JLabel price = new JLabel("Price: ₹" + product.getPrice(), JLabel.CENTER);

        JComboBox<Integer> quantityCombo = new JComboBox<>();
        for (int q = 0; q <= 10; q++) quantityCombo.addItem(q);

        JButton addToCart = new JButton("Add to Cart");

        addToCart.addActionListener(e -> {
            int newQuantity = (int) quantityCombo.getSelectedItem();
            int oldQuantity = cartQuantities.getOrDefault(product.getItem(), 0);

            int delta = newQuantity - oldQuantity;
            totalAmount += delta * product.getPrice();
            totalLabel.setText("Total: ₹" + totalAmount);

            cartQuantities.put(product.getItem(), newQuantity);
            itemPrices.put(product.getItem(), product.getPrice());
        });

        card.add(name);
        card.add(price);
        card.add(quantityCombo);
        card.add(addToCart);

        itemPanel.add(card);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CustomerDashboard("Customer").setVisible(true));
    }
}
