import java.util.ArrayList;
public class Customer extends User
{
    private String customerName;
    private ArrayList<Order> orders=null;
    private ShoppingCart shoppingCart;

    public Customer(String userId, String password, String customerName) 
    {
        super(userId, password);
        this.customerName = customerName;
        this.orders = new ArrayList<>();
        this.shoppingCart = new ShoppingCart();
    }

    public String getCustomerName()
    {
        return customerName;
    }
    
public void placeOrder() 
{
        Order newOrder = new Order(orders.size() + 1, shoppingCart.getProducts());
        orders.add(newOrder);
    }


    public String toString()
    {
        return "Customer name: "+customerName;
    }
}
