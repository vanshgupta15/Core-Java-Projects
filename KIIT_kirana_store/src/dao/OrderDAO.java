package dao;
import config.DBConnection;
import java.sql.*;
import java.util.List;
import model.Order;
import model.OrderItem;

public class OrderDAO {

    public int placeOrder(Order order) throws SQLException {
        String insertOrderSQL = "INSERT INTO orders (user_id, total_amount, payment_type, status) VALUES (?, ?, ?, ?)";
        String insertOrderItemSQL = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement orderStmt = null;
        PreparedStatement itemStmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection(); // Use your DB utility class
            conn.setAutoCommit(false); // transaction start

            // Insert into orders
            orderStmt = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS);
            orderStmt.setInt(1, order.getUserId());
            orderStmt.setInt(2, order.getTotalAmount());
            orderStmt.setString(3, order.getPaymentType());
            orderStmt.setString(4, order.getStatus());

            int affectedRows = orderStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            rs = orderStmt.getGeneratedKeys();
            int orderId;
            if (rs.next()) {
                orderId = rs.getInt(1);
            } else {
                throw new SQLException("Creating order failed, no ID obtained.");
            }

            // Insert each item
            itemStmt = conn.prepareStatement(insertOrderItemSQL);
            List<OrderItem> items = order.getItems();
            for (OrderItem item : items) {
                itemStmt.setInt(1, orderId);
                itemStmt.setInt(2, item.getProductId());
                itemStmt.setInt(3, item.getQuantity());
                itemStmt.setInt(4, item.getPrice());
                itemStmt.addBatch();
            }
            itemStmt.executeBatch();

            conn.commit(); // transaction commit
            return orderId;

        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (orderStmt != null) orderStmt.close();
            if (itemStmt != null) itemStmt.close();
            if (conn != null) conn.setAutoCommit(true); conn.close();
        }
    }
}
