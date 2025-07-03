package dao;
import config.DBConnection;
import java.sql.*;
import java.util.ArrayList;
public class FormDAO 
{
    ArrayList<String> category= new ArrayList<>();
    ArrayList<String> subcategory= new ArrayList<>();
    public ArrayList getCategories()
    {
        
        Connection con= DBConnection.getConnection();
        try (Statement stmt = con.createStatement()) 
        {
            String Sql = "select distinct(category) from category_master;";
            ResultSet rs = stmt.executeQuery(Sql);
            while (rs.next()) 
            {
                category.add(rs.getNString("category"));
            }
            return category;
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return null;
    }    
    public ArrayList getSubCategories(String category)
    {
        Connection con= DBConnection.getConnection();
        try (Statement stmt = con.createStatement()) 
        {
            String Sql = "select sub_category  from category_master where category='"+category+"';";
            ResultSet rs = stmt.executeQuery(Sql);
            while (rs.next()) 
            {
                subcategory.add(rs.getNString("sub_category"));
            }
            return subcategory;
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return null;
    }   
}
