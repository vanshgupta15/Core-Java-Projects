import java.io.*;
import java.util.Scanner;
public class Diary 
{
    public static void main(String[] args) 
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the password:");
        
        String password= sc.nextLine();
        if(password.equals("khuljasimsim"))
        {
        boolean c=true;
        while(c==true)
        {
            System.out.println("Do you want to continue if yes then press y else n");
            char option= sc.next().charAt(0);
            if(option=='y')
            {        System.out.println("Choose from the options:-\n1. Write a diary\n2. Read a diary");
        int input= sc.nextInt();
        switch(input)
        {
            case 1:
            {
                try
                {
                    System.out.println("Enter your diary name with extension");
                    String buffer1= sc.nextLine();
                    String f= sc.nextLine();
                    FileWriter fw= new FileWriter("Data/"+f);
                    System.out.println("Enter your new diary entry: ");
                    String s= sc.nextLine();
                    fw.write(s);
                    fw.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                break;
            }
            case 2:
            {
                try
                {
                    System.out.println("Which diary do you want to print:");
                    String buffer2= sc.nextLine();
                    String s= sc.nextLine();
                    FileReader fr= new FileReader(s);
                    int i=0;
                    while((i=fr.read())!=-1)
                    {
                        System.out.print((char)i);
                    }
                    fr.close();
                    System.out.println();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                break;
            }
        }
        }
        else
        {
            c=false;
        }
        }
    }
    else
    {
        System.out.println("Wrong password");
        System.exit(0);
    }
    }
}