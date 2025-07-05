package service;
import dao.ProductDAO;
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

    public List<Product> getAllProducts() 
    {
        return new ProductDAO().getAllProducts();
    }

}
