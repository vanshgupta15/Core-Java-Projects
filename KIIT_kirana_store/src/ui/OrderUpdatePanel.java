import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import service.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OrderUpdatePanel extends JFrame {
    private JTable orderTable;
    private JButton refreshButton, updateButton;
    private DefaultTableModel tableModel;
    private JComboBox<String>[] statusDropdowns;

    private String[] statusOptions = {"Processing", "Shipped", "Delivered"};

    public OrderUpdatePanel() {
        setTitle("Update Orders");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        refreshButton = new JButton("Refresh");
        topPanel.add(refreshButton);
        add(topPanel, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(new Object[]{"Order ID", "User ID", "Date", "Current Status", "New Status"}, 0);
        orderTable = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };
        JScrollPane scrollPane = new JScrollPane(orderTable);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        updateButton = new JButton("Update Status");
        bottomPanel.add(updateButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Load Data Initially
        loadOrders();

        // Button Actions
        refreshButton.addActionListener(e -> loadOrders());
        updateButton.addActionListener(e -> updateStatuses());
    }

    private void loadOrders() {
        tableModel.setRowCount(0);
        OrderService service = new OrderService();
        List<Order> orders = service.getLast10ActiveOrders();

        statusDropdowns = new JComboBox[orders.size()];

        for (int i = 0; i < orders.size(); i++) {
            Order o = orders.get(i);
            JComboBox<String> dropdown = new JComboBox<>(statusOptions);
            dropdown.setSelectedItem(o.getStatus());
            statusDropdowns[i] = dropdown;

            tableModel.addRow(new Object[]{
                o.getOrderId(),
                o.getUserId(),
                o.getOrderDate().toString(),
                o.getStatus(),
                dropdown
            });
        }

        orderTable.getColumn("New Status").setCellRenderer(new ComboBoxTableCellRenderer());
        orderTable.getColumn("New Status").setCellEditor(new DefaultCellEditor(new JComboBox<>(statusOptions)));
    }

    private void updateStatuses() {
        OrderService service = new OrderService();

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            int orderId = (int) tableModel.getValueAt(i, 0);
            String newStatus = (String) tableModel.getValueAt(i, 4);
            service.updateOrderStatus(orderId, newStatus);
        }

        JOptionPane.showMessageDialog(this, "Statuses updated successfully.");
        loadOrders();
    }

    // Renderer to display combo box in table
    class ComboBoxTableCellRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
        boolean hasFocus, int row, int column) {
            JComboBox<String> comboBox = new JComboBox<>(statusOptions);
            comboBox.setSelectedItem(value);
            return comboBox;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OrderUpdatePanel().setVisible(true));
    }
}
