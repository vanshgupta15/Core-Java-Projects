import java.io.*;

public class StudentFileHandler 
{
    public void saveStudent(Student s1)
    {
        try
        {
            int f= s1.getId();
            FileOutputStream fout= new FileOutputStream("Data/"+f+"");
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

    public void searchStudent(int id) //deserializing
    {
        try
        {
            FileInputStream fileIn = new FileInputStream("Data/"+id+"");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Student student= (Student)in.readObject();
            System.out.println(student);
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
            for(int i=0;i<paths.length;i++)
            {
                System.out.print(paths[i]);
                FileInputStream fileIn = new FileInputStream("Data/"+paths[i]);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Student student= (Student)in.readObject();
                System.out.println(" "+student.getName());
                in.close();
                fileIn.close();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
