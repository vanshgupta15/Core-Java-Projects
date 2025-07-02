package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBConnection 
{
    public static Connection getConnection()
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
        Connection conn = null;
        try 
        {
            conn = DriverManager.getConnection(url, user, password);
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
        }
        return conn;
    }
}