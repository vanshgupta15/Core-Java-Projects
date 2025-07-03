package ui;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import service.FormService;

public class ProductManagementPanel extends JFrame {

    private JTable productTable;
    private DefaultTableModel tableModel;
    private JTextField itemField, priceField, quantityField;
    private JComboBox<String> categoryCombo, subCategoryCombo;
    private JButton addButton, updateButton, deleteButton, refreshButton;

    private HashMap<String, String[]> subCategoryMap;

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

        FormService formService= new FormService();
        ArrayList<String> category= new ArrayList<>();
        category= formService.getCategories();
        System.out.println(category);
        String[] categoryArr = new String[category.size()];
        categoryArr = category.toArray(categoryArr);

        itemField = new JTextField();
        priceField = new JTextField();
        quantityField = new JTextField();
        categoryCombo = new JComboBox<>(categoryArr);
        subCategoryCombo = new JComboBox<>();

        // Map categories to subcategories
        subCategoryMap = new HashMap<>();
        subCategoryMap.put("Snacks", new String[]{"Mobiles", "Laptops", "Accessories"});
        subCategoryMap.put("Stationery", new String[]{"Men", "Women", "Kids"});

        categoryCombo.addActionListener(e -> updateSubCategories());
        updateSubCategories();

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

        // Button Actions (to be implemented)
        addButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Add Product logic goes here"));
        updateButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Update Product logic goes here"));
        deleteButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Delete Product logic goes here"));
        refreshButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Refresh Table logic goes here"));
    }

    private void updateSubCategories() {
        String selectedCategory = (String) categoryCombo.getSelectedItem();
        subCategoryCombo.removeAllItems();
        if (selectedCategory != null && subCategoryMap.containsKey(selectedCategory)) {
            for (String subCat : subCategoryMap.get(selectedCategory)) {
                subCategoryCombo.addItem(subCat);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductManagementPanel().setVisible(true));
    }
}
