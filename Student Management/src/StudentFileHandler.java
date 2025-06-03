import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class StudentFileHandler 
{
    public void saveStudent(Student s1)
    {
        try
        {
            int f= s1.getId();
            FileOutputStream fout= new FileOutputStream("Data/"+f+".txt");
            ObjectOutputStream o= new ObjectOutputStream(fout);
            o.writeObject(s1);
            o.close();
            fout.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
