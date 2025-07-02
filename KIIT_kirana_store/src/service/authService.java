package service;
import dao.UserDAO;
import model.User;
public class AuthService
{
    public User loginUser(String userName, String password)
    {
        UserDAO userDAO = new UserDAO();
        User user=userDAO.login(userName, password);
        return user;
    }
}
