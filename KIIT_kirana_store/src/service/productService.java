package service;
import java.util.List;

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

    public List<Product> getAllProducts() 
    {
        return new ProductDAO().getAllProducts();
    }

}
