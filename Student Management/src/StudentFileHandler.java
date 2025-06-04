import java.io.*;

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

    public void searchStudent(int id)
    {
        try
        {
            FileInputStream fileIn = new FileInputStream("Data/"+id+".txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            System.out.println(in.readObject());
            in.close();
            fileIn.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void studentDisplay()
    {
        try
        {
            File file= new File("C:\\Vansh_codings\\codecoach\\Core-java-projects\\Student Management\\Data");
            String [] paths=file.list();
            for(String path:paths)
            {
                System.out.println(path);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
