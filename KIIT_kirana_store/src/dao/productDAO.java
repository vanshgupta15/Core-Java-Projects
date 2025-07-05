package dao;
import config.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

    public boolean updateProduct(Product product) 
    {
        Connection con = DBConnection.getConnection();
        try (Statement stmt = con.createStatement()) {
            String sql = "UPDATE products SET item = '"+product.getItem()+"', price = "+product.getPrice()+",catg_master_id = (SELECT id FROM category_master WHERE category = '"+product.getCategory()+"' AND sub_category = '"+product.getSubCategory()+"'),quantity = "+product.getQuantity()+" WHERE id = "+product.getId()+";";
            int rowsAffected = stmt.executeUpdate(sql);
            return rowsAffected > 0;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteProduct(Product product) 
    {
        Connection con = DBConnection.getConnection();
        try (Statement stmt = con.createStatement()) {
            String sql = "delete from products where id="+product.getId()+";";
            int rowsAffected = stmt.executeUpdate(sql);
            return rowsAffected > 0;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return false;
    }

    public List<Product> getAllProducts() 
    {
        List<Product> productList = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT p.id, p.item, p.price, p.quantity, c.category, c.sub_category " +
                "FROM products p JOIN category_master c ON p.catg_master_id = c.id")) {

            while (rs.next()) {
                Product product = new Product(
                    rs.getInt("id"),
                    rs.getString("item"),
                    rs.getInt("price"),
                    rs.getInt("quantity"),
                    rs.getString("category"),
                    rs.getString("sub_category")
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

}
