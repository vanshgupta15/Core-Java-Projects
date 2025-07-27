package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
        String url = "jdbc:mysql://localhost:3306/kiit_kirana_store"; 
        String user = "root"; 
        String password = "root"; 
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