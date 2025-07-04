package dao;
import config.DBConnection;
import java.sql.*;
import model.Product;
public class ProductDAO 
{
    public boolean addProduct(Product product) 
    {
        Connection con = DBConnection.getConnection();
        try (Statement stmt = con.createStatement()) {
            String sql = "INSERT INTO products(item, price, catg_master_id, quantity) " +
                        "VALUES ('" + product.getItem() + "', " +
                        product.getPrice() + ", " +
                        "(SELECT id FROM category_master WHERE category = '" +
                        product.getCategory() + "' AND sub_category = '" +
                        product.getSubCategory() + "'), " +
                        product.getQuantity() + ")";
            int rowsAffected = stmt.executeUpdate(sql);
            return rowsAffected > 0;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return false;
    }
}
