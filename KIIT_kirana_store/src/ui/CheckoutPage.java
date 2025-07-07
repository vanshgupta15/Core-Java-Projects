package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
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

        // Table for cart items
        tableModel = new DefaultTableModel(new Object[]{"Item", "Quantity", "Price", "-", "+"}, 0);
        cartTable = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return column == 3 || column == 4; // only allow +/- buttons
            }
        };

        loadCartData();

        JScrollPane scrollPane = new JScrollPane(cartTable);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel with total and checkout
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalLabel = new JLabel("Total: ₹" + calculateTotal());
        checkoutButton = new JButton("Checkout");

        checkoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Order placed successfully!");
            this.dispose();
        });

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

            JButton minusButton = new JButton("-");
            JButton plusButton = new JButton("+");

            Object[] row = new Object[]{item, qty, "₹" + (qty * price), minusButton, plusButton};
            tableModel.addRow(row);
        }

        cartTable.getColumn("-").setCellRenderer(new ButtonRenderer());
        cartTable.getColumn("-").setCellEditor(new ButtonEditor(new JCheckBox(), -1));
        cartTable.getColumn("+").setCellRenderer(new ButtonRenderer());
        cartTable.getColumn("+").setCellEditor(new ButtonEditor(new JCheckBox(), +1));
    }

    private int calculateTotal() {
        int total = 0;
        for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
            total += entry.getValue() * itemPrices.getOrDefault(entry.getKey(), 0);
        }
        return total;
    }

    class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value == null ? "" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private int delta;
        private String currentItem;
        private boolean clicked;

        public ButtonEditor(JCheckBox checkBox, int delta) {
            super(checkBox);
            this.button = new JButton();
            this.delta = delta;
            this.button.addActionListener(this::actionPerformed);
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            currentItem = (String) table.getValueAt(row, 0);
            button.setText(value.toString());
            clicked = true;
            return button;
        }

        public Object getCellEditorValue() {
            clicked = false;
            return button.getText();
        }

        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }

        private void actionPerformed(ActionEvent e) {
            int currentQty = cartItems.getOrDefault(currentItem, 0);
            int newQty = Math.max(0, currentQty + delta);
            cartItems.put(currentItem, newQty);
            totalLabel.setText("Total: ₹" + calculateTotal());
            loadCartData();
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> cart = new HashMap<>();
        cart.put("Item A", 2);
        cart.put("Item B", 1);

        HashMap<String, Integer> prices = new HashMap<>();
        prices.put("Item A", 200);
        prices.put("Item B", 150);

        SwingUtilities.invokeLater(() -> new CheckoutPage(cart, prices).setVisible(true));
    }
}