
import java.util.HashMap;

public class ECommercePlatform 
{
    private HashMap<String, Customer> customers;
    private HashMap<Long, Product> products;

    public ECommercePlatform() {
        this.customers = new HashMap<>();
        this.products = new HashMap<>();
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getUserId(), customer);
    }

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    public Customer getCustomer(String userId) {
        return customers.get(userId);
    }

    public Product getProduct(long productId) {
        return products.get(productId);
    }

}    