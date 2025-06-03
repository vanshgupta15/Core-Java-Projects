public class StudentManager 
{
    public boolean createStudent(Student student)
    {
        StudentFileHandler a= new StudentFileHandler();
        a.saveStudent(student);
        System.out.println(student.course());
        return true;
    }
}