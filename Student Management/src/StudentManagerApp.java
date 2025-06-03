import java.util.*;
public class StudentManagerApp 
{
    Scanner sc= new Scanner(System.in);
    
    public static void main(String[] args) 
    {
        boolean check1=true;
        StudentManagerApp app= new StudentManagerApp();
        while(check1==true)
        {
            int option= app.showMenu();
            if(option==4)
            {
                check1=false;
            }
            app.processMenu(option);
        }
    }
    public int showMenu()
        {
            System.out.println("Welcome to student manager app\n\nChoose from the menu below:-");
            System.out.println("1.Create new student\n" + //
                                "2.Get details of a student\n" + //
                                "3.Get all student\n"+//
                                "4.Exit");
            int menu= sc.nextInt();
            return menu;
        }
    public void processMenu(int option)
    {
        switch(option)
        {
            case 1:
            {
                StudentManager s= new StudentManager();
                System.out.println("Enter student id:");
                int id= sc.nextInt();
                System.out.println("What is the name of the student: ");
                String name= sc.nextLine();
                String buffer1=sc.nextLine();
                System.out.println("Enter age of the student:");
                int age= sc.nextInt();
                System.out.println("Enter the enrolled course: ");
                String course= sc.nextLine();
                String buffer2=sc.nextLine();
                Student student= new Student(id, name, age, course);
                s.createStudent(student);
            }
            case 2:
            {
            }
            case 3:
            {
            }
        }
    }
}
