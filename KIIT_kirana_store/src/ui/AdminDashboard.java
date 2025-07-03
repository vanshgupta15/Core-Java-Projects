package ui;

import java.awt.*;
import javax.swing.*;

public class AdminDashboard extends JFrame {

    private JButton manageProductsButton;
    private JButton manageCategoriesButton;
    private JButton logoutButton;

    public AdminDashboard() {
        setTitle("Admin Dashboard - Shopping Center");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome, Shopkeeper!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        manageProductsButton = new JButton("Manage Products");
        manageCategoriesButton = new JButton("Manage Categories");
        logoutButton = new JButton("Logout");

        buttonPanel.add(manageProductsButton);
        buttonPanel.add(manageCategoriesButton);
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Action listeners
        manageProductsButton.addActionListener(e -> {
            // TODO: Open ProductManagementPanel
            JOptionPane.showMessageDialog(this, "Opening Product Management...");
            // new ProductManagementPanel().setVisible(true);
        });

        manageCategoriesButton.addActionListener(e -> {
            // TODO: Open CategoryManagementPanel
            JOptionPane.showMessageDialog(this, "Opening Category Management...");
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
