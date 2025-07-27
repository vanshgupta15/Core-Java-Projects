package ui;

import dao.OrderDAO;
import model.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.List;

public class OrderUpdateFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private OrderDAO orderDAO = new OrderDAO();
    private List<Order> orderList;

    public OrderUpdateFrame() {
        setTitle("Update Orders");
        setSize(900, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {"Order ID", "User ID", "Date", "Payment Type", "Status", "Total Amount", "Update"};

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6; // Only Update column is editable
            }
        };

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // ✅ Set dropdown with ENUM options
        String[] statusOptions = {"pending", "completed", "cancelled"};
        JComboBox<String> comboBox = new JComboBox<>(statusOptions);
        table.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(comboBox));

        loadOrders();

        // ✅ Save Button
        JButton updateBtn = new JButton("Update Status");
        updateBtn.addActionListener(e -> updateStatus());
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(updateBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadOrders() {
        orderList = orderDAO.getLast10ActiveOrders(); // get orders with status 'pending'
        for (Order order : orderList) {
            tableModel.addRow(new Object[]{
                    order.getOrderId(),
                    order.getUserId(),
                    order.getOrderDate(),
                    order.getPaymentType(),
                    order.getStatus(),
                    order.getTotalAmount(),
                    order.getStatus() // default value in dropdown
            });
        }
    }

    private void updateStatus() {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            int orderId = (int) tableModel.getValueAt(i, 0);
            String currentStatus = (String) tableModel.getValueAt(i, 4);
            String newStatus = (String) tableModel.getValueAt(i, 6);

            if (!currentStatus.equals(newStatus)) {
                boolean updated = orderDAO.updateOrderStatus(orderId, newStatus);
                if (updated) {
                    tableModel.setValueAt(newStatus, i, 4); // reflect update in UI
                    JOptionPane.showMessageDialog(this, "Order " + orderId + " updated to " + newStatus);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update order " + orderId);
                }
            }
        }
    }
}
