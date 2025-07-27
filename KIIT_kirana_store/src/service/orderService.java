package service;
import dao.OrderDAO;
import java.util.List;
import model.Order;

public class OrderService 
{
    public boolean placeOrder(Order order) throws Exception
    {
        OrderDAO orderDAO= new OrderDAO();
        orderDAO.placeOrder(order);
        return true;
    }
    public List<Order> getLast10ActiveOrders() 
    {
    return new OrderDAO().fetchLast10ActiveOrders();
    }

    public boolean updateOrderStatus(int orderId, String status) 
    {
        return new OrderDAO().updateOrderStatus(orderId, status);
    }

}
