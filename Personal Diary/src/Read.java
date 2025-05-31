import java.io.FileReader;
import java.util.Scanner;

public class Read 
{
    public static void main(String[] args) 
    {
        try
        {
            Scanner sc= new Scanner(System.in);
            System.out.println("Which diary do you want to print:");
            String s= sc.nextLine();
            FileReader fr= new FileReader(s);
            int i=0;
            while((i=fr.read())!=-1)
            {
                System.out.print((char)i);
            }
        fr.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
