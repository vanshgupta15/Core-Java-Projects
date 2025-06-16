public class ECommerceDemo 
{
    public static void main(String[] args) 
    {
        ECommercePlatform platform = new ECommercePlatform();

        // Adding products
        Product laptop = new Product(101, "Laptop", 75000.0);
        Product phone = new Product(102, "Smartphone", 50000.0);
        platform.addProduct(laptop);
        platform.addProduct(phone);

        // Adding a customer
        Customer customer = new Customer("cust123", "pass123", "Vansh");
        platform.addCustomer(customer);

        // Customer adds products to shopping cart
        customer.(shoppingCart.addProduct(laptop));
        customer.(shoppingCart.addProduct(phone));

        // Customer places order
        customer.placeOrder();

        // Display order details
        System.out.println("Customer: " + customer.getCustomerName());
        System.out.println("Total Orders: " + customer.getOrders().size());
        System.out.println("Latest Order Total Price: ₹" + customer.getOrders());
        System.out.println("Total amount to be paid: "+.customer.getTotalPrice());
    }

}

