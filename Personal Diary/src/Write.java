import java.io.*;
import java.util.Scanner;
public class Write 
{
    public static void main(String[] args) 
    {
        try
        {
            Scanner sc= new Scanner(System.in);
            System.out.println("Enter your diary name with extension");
            String f= sc.nextLine();
            FileWriter fw= new FileWriter(f);
            System.out.println("Enter your new diary entry: ");
            String s= sc.nextLine();
            fw.write(s);
            fw.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}