package service;
import dao.FormDAO;
import java.util.ArrayList;
public class FormService 
{
    public ArrayList getCategories()
    {
        FormDAO formDAO= new FormDAO();
        ArrayList<String> category= new ArrayList<>();
        category=formDAO.getCategories();
        return category;
    }    
    public ArrayList getSubCategories(String category)
    {
        FormDAO formDAO= new FormDAO();
        ArrayList<String> subcategory= new ArrayList<>();
        subcategory=formDAO.getSubCategories(category);
        return subcategory;
    }   
}
