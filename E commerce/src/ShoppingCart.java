
import java.util.ArrayList;

public class ShoppingCart 
{
    double sum=0.0;
    private ArrayList<Product> products;

    public ShoppingCart() 
    {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) 
    {
        products.add(product);
        //calculate here
    }

    public void removeProduct(Product product)
    {
        products.remove(product);
    }
    for (Product product : products) {
        public void  calculateTotal()
        {
            double sum=sum+product.getPrice();
        }
    }
    public ArrayList<Product> getProducts() 
    {
        return products;
    }

}
