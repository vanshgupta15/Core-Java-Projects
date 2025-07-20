package model;

import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private int userId;
    private int totalAmount;
    private String paymentType; // "cash" or "online"
    private String status;      // "placed", "cancelled", "delivered"
    private Date orderDate;

    private List<OrderItem> items; // List of items in the order

    // Constructors
    public Order() {}

    public Order(int userId, int totalAmount, String paymentType, String status) {
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.paymentType = paymentType;
        this.status = status;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getTotalAmount() { return totalAmount; }
    public void setTotalAmount(int totalAmount) { this.totalAmount = totalAmount; }

    public String getPaymentType() { return paymentType; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
}
