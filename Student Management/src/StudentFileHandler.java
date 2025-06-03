import java.util.Scanner;
public class StudentFileHandler 
{
    public static void main(String[] args) 
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("Number of students");
        int nstudents= sc.nextInt();
        for (int i = 0; i <= nstudents; i++) 
        {
            System.out.println("Enter the id of the student: ");
            long id=sc.nextInt();
            System.out.println("Enter the students name: ");
            String name= sc.nextLine();
            System.out.println("Enter the age of the student: ");
            int age= sc.nextInt();
            System.out.println("Enter the course of the student");
            String course= sc.nextLine();
            Student s= new Student(id, name, age, course);
        }
        
    }

}
