package dao;
import config.DBConnection;
import java.sql.*;
import model.Order;

public class OrderDAO 
{
    public boolean placeOrder(Order order)
    {
        Connection con = DBConnection.getConnection();
        try (Statement stmt = con.createStatement()) {
            String firstSql= "INSERT INTO orders (user_id, payment_type, total_amount) VALUES ("+order.getUserId()+", "+order.getPaymentType()+","+order.getTotalAmount()+");";
            ResultSet rs = stmt.executeQuery(FirstSql);
            int orderId;
            while (rs.next()) 
            {
                orderId= rs.getInt("order_id");
            }
            String secondSql= 
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return false;
    }
}
