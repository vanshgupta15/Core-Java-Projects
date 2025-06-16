
import java.util.ArrayList;

public class Order 
{
    private long orderId;
    private ArrayList<Product> products;
    private double totalPrice;

    
    public Order(long orderId, ArrayList<Product> products,double totalPrice) 
    {
        this.orderId = orderId;
        this.products = new ArrayList<>(products);
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() 
    {
        return totalPrice;
    }
}
