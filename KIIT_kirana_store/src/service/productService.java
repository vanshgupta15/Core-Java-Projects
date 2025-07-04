package service;
import dao.ProductDAO;
import model.Product;
public class ProductService 
{
    public boolean addProduct(Product product)
    {
        ProductDAO productDAO= new ProductDAO();
        productDAO.addProduct(product);
        return true;
    }
}
