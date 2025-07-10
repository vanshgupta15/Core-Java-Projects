package model;

import java.util.Date;

public class Order 
{
    private int orderId;
    private int userId;
    private Date orderDate;
    private String paymentType;
    private String status;
    private int totalAmount;

    public Order(int oderId,int userId,Date orderDate,String paymentType,String status,int totalAmount)
    {
        this.orderId=orderId;
        this.userId=userId;
        this.orderDate=orderDate;
        this.paymentType=paymentType;
        this.status=status;
        this.totalAmount=totalAmount;
    }
    //getters
    public int getOrderId()
    {
        return orderId;
    }
    public int getUserId()
    {
        return userId;
    }
    public Date getOderDate()
    {
        return orderDate;
    }
    public String getPaymentType()
    {
        return paymentType;
    }
    public String getStatus()
    {
        return status;
    }
    public int getTotalAmount()
    {
        return totalAmount;
    }
}