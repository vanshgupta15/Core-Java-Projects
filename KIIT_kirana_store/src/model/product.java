package model;

public class Product 
{
    private int id;
    private String item;
    private int price;
    private int quantity;
    private String category;
    private String subCategory;

    public Product(String item, int price, int quantity, String category, String subCategory)
    {
        this.item=item;
        this.price=price;
        this.quantity=quantity;
        this.category=category;
        this.subCategory=subCategory;
    }
    public Product(int id, String item, int price, int quantity, String category, String subCategory)
    {
        this.id=id;
        this.item=item;
        this.price=price;
        this.quantity=quantity;
        this.category=category;
        this.subCategory=subCategory;
    }

    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public String getItem()
    {
        return item;
    }
    public void setItem(String item)
    {
        this.item=item;
    }
    public int getPrice()
    {
        return price;
    }
    public void setPrice(int price)
    {
        this.price=price;
    }
    public int getQuantity()
    {
        return quantity;
    }
    public void setQuantity(int quantity)
    {
        this.quantity=quantity;
    }
    public String getCategory()
    {
        return category;
    }
    public void setCategory(String category)
    {
        this.category=category;
    }
    public String getSubCategory()
    {
        return subCategory;
    }
    public void setSubCategory(String subCategory)
    {
        this.subCategory=subCategory;
    }

    @Override
    public String toString() 
    {
        return "Product{" +
            "id=" + id +
            ", item='" + item + '\'' +
            ", price=" + price +
            ", quantity=" + quantity +
            ", category='" + category + '\'' +
            ", subCategory='" + subCategory + '\'' +
            '}';
    }

}