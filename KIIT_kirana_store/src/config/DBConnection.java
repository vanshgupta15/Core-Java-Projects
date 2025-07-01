package config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBConnection 
{
    public void Connection()
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) 
        {
            e.printStackTrace();
        }
        // create connection
        String url = "jdbc:mysql://localhost:3306/kiit_kirana_store"; // replace with your database URL
        String user = "root"; // replace with your database username
        String password = "root"; // replace with your database password
        try (Connection conn = DriverManager.getConnection(url, user, password))
        {
            try (Statement stmt = conn.createStatement()) 
            {
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
}