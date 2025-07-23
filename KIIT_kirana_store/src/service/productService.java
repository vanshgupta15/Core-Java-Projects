package service;
import config.DBConnection;
import dao.ProductDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.Product;
public class ProductService 
{
    public boolean addProduct(Product product)
    {
        ProductDAO productDAO= new ProductDAO();
        productDAO.addProduct(product);
        return true;
    }
    
    public boolean updateProduct(Product product)
    {
        ProductDAO productDAO= new ProductDAO();
        productDAO.updateProduct(product);
        return true;
    }

    public boolean deleteProduct(Product product)
    {
        ProductDAO productDAO= new ProductDAO();
        productDAO.deleteProduct(product);
        return true;
    }

    public int getProductIdByName(String itemName) {
        int productId = -1;
        String sql = "SELECT id FROM products WHERE item = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, itemName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                productId = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productId;
    }

    public List<Product> getAllProducts() 
    {
        return new ProductDAO().getAllProducts();
    }

}
