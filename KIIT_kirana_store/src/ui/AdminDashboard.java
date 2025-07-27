package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AdminDashboard extends JFrame {

    private JButton manageProductsButton;
    private JButton manageCategoriesButton;
    private JButton viewReportsButton;
    private JButton manageTransactionsButton;
    private JButton updateOrdersButton;
    private JButton logoutButton;

    public AdminDashboard() {
        setTitle("Admin Dashboard - Shopping Center");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome, Shopkeeper!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        manageProductsButton = new JButton("Manage Products");
        manageCategoriesButton = new JButton("Manage Categories");
        viewReportsButton = new JButton("View Stock Reports");
        manageTransactionsButton = new JButton("View Transactions");
        updateOrdersButton = new JButton("Update Orders");
        logoutButton = new JButton("Logout");

        // Add buttons to the panel
        buttonPanel.add(manageProductsButton);
        buttonPanel.add(manageCategoriesButton);
        buttonPanel.add(viewReportsButton);
        buttonPanel.add(manageTransactionsButton);
        buttonPanel.add(updateOrdersButton);
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Action Listeners
        manageProductsButton.addActionListener(e -> {
            new ProductManagementPanel().setVisible(true);
        });

        manageCategoriesButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Opening Category Management...");
        });

        viewReportsButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Opening Stock Report Panel...");
            // TODO: new StockReportPanel().setVisible(true);
        });

        manageTransactionsButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Opening Transactions Panel...");
            // TODO: new TransactionPanel().setVisible(true);
        });

        updateOrdersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new OrderUpdateFrame().setVisible(true);
            }
        });

        logoutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                this.dispose();
                new LoginFrame().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminDashboard().setVisible(true));
    }
}
