package ui;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Product;
import service.FormService;
import service.ProductService;

public class ProductManagementPanel extends JFrame {

    private JTable productTable;
    private DefaultTableModel tableModel;
    private JTextField itemField, priceField, quantityField;
    private JComboBox<String> categoryCombo, subCategoryCombo;
    private JButton addButton, updateButton, deleteButton, refreshButton;

    private ArrayList<String> categoryList;

    public ProductManagementPanel() {
        setTitle("Product Management");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table setup
        tableModel = new DefaultTableModel(new String[]{"ID", "Item", "Price", "Quantity", "Category", "Sub-Category"}, 0);
        productTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(productTable);

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Product Details"));

        FormService formService = new FormService();
        categoryList = formService.getCategories();

        itemField = new JTextField();
        priceField = new JTextField();
        quantityField = new JTextField();

        categoryCombo = new JComboBox<>();
        subCategoryCombo = new JComboBox<>();

        // Add a blank/default option at the start
        categoryCombo.addItem("-- Select Category --");
        for (String category : categoryList) {
            categoryCombo.addItem(category);
        }

        categoryCombo.addActionListener(e -> updateSubCategories());

        formPanel.add(new JLabel("Item:"));
        formPanel.add(itemField);
        formPanel.add(new JLabel("Price:"));
        formPanel.add(priceField);
        formPanel.add(new JLabel("Quantity:"));
        formPanel.add(quantityField);
        formPanel.add(new JLabel("Category:"));
        formPanel.add(categoryCombo);
        formPanel.add(new JLabel("Sub-Category:"));
        formPanel.add(subCategoryCombo);

        // Buttons
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        refreshButton = new JButton("Refresh");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        add(formPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        addButton.addActionListener(e -> addProduct());
        updateButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Update Product logic goes here"));
        deleteButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Delete Product logic goes here"));
        refreshButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Refresh Table logic goes here"));
    }

    private void updateSubCategories() {
        subCategoryCombo.removeAllItems();
        String selectedCategory = (String) categoryCombo.getSelectedItem();

        if (selectedCategory == null || selectedCategory.equals("-- Select Category --")) {
            return;
        }

        FormService formService = new FormService();
        ArrayList<String> subCategoryList = formService.getSubCategories(selectedCategory);

        if (subCategoryList != null && !subCategoryList.isEmpty()) {
            for (String sub : subCategoryList) {
                subCategoryCombo.addItem(sub);
            }
        }
    }

    private void addProduct() {
        try {
            String item = itemField.getText().trim();
            String priceText = priceField.getText().trim();
            String quantityText = quantityField.getText().trim();
            String category = (String) categoryCombo.getSelectedItem();
            String subCategory = (String) subCategoryCombo.getSelectedItem();

            if (item.isEmpty() || priceText.isEmpty() || quantityText.isEmpty() ||
                category == null || category.equals("-- Select Category --") || subCategory == null) {
                JOptionPane.showMessageDialog(this, "Please fill all fields and select valid category/sub-category.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int price = Integer.parseInt(priceText);
            int quantity = Integer.parseInt(quantityText);

            Product product = new Product(item, price, quantity, category, subCategory);
            ProductService productService = new ProductService();
            boolean success = productService.addProduct(product);

            if (success) {
                JOptionPane.showMessageDialog(this, "Product added successfully!");
                clearFormFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add product.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Price and Quantity must be valid numbers.", "Input Error", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An unexpected error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFormFields() {
        itemField.setText("");
        priceField.setText("");
        quantityField.setText("");
        categoryCombo.setSelectedIndex(0); // "-- Select Category --"
        subCategoryCombo.removeAllItems();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductManagementPanel().setVisible(true));
    }
}
