package service;

public class AuthService
{
    private String userName;
    private String password;

    public AuthService(String username, String password)
    {
        this.userName=username;
        this.password=password;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String password)
    {
        this.userName=userName;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }

}
