package service;
import dao.OrderDAO;
import model.Order;

public class OrderService 
{
    public boolean placeOrder(Order order)
    {
        OrderDAO orderDAO= new OrderDAO();
        orderDAO.placeOrder(order);
        return true;
    }
}
