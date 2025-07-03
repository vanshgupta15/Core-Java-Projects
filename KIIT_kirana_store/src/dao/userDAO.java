package dao;
import config.DBConnection;
import java.sql.*;
import model.User;
public class UserDAO 
{
    Connection con= DBConnection.getConnection();
    public User login(String userName, String password)
    {
        User user=null;
        try (Statement stmt = con.createStatement()) 
        {
            // String insertSql = "select * from users where username=? and password=?";
            // PreparedStatement pstmt = con.prepareStatement(insertSql);
            // pstmt.setString(1, userName);
            // pstmt.setString(2, password);
            // ResultSet rs = pstmt.executeQuery(insertSql);

            String Sql = "select * from users where username='"+userName+"' and password='"+password+"';";

            ResultSet rs = stmt.executeQuery(Sql);
            while (rs.next()) 
            {
                    int userid= rs.getInt("user_id");
                    String usernameFromDB = rs.getString("username"); 
                    String passWordFromDB = rs.getString("password");
                    boolean isActive=rs.getBoolean("is_active");
                    System.out.println(usernameFromDB);
                    System.out.println(passWordFromDB);
                    System.out.println(isActive);
                    user = new User(userid, usernameFromDB, password, passWordFromDB);
                    return user;
            }
            
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return null;
    }
}
