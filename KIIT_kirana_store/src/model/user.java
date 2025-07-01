package model;

import java.util.Date;

public class User 
{
    private int userId;
    private String userName;
    private String password;
    private String role;
    private boolean isActive;
    private Date createdAt;

    public User(int userId, String userName, String password, String role)
    {
        this.userName=userName;
        this.userId=userId;
        this.role=role;
        this.password=password;
    }

    public User(String userName, String password, String role)
    {
        this.userName=userName;
        this.role=role;
        this.password=password;
    }

    public int getUserId()
    {
        return userId;
    }
    public void setUserId(int userId)
    {
        this.userId=userId;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
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

    public String getRole()
    {
        return role;
    }
    public void setRole(String role)
    {
        this.role=role;
    }

    public boolean getIsActive()
    {
        return isActive;
    }
    public void setIsActive(boolean isActive)
    {
        this.isActive=isActive;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt)
    {
        this.createdAt=createdAt;
    }

    public boolean isAdmin()
    {
        if(role.equalsIgnoreCase("Admin"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
