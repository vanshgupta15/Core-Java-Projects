public class StudentManager 
{
    public boolean createStudent(Student student)
    {
        StudentFileHandler saver= new StudentFileHandler();
        saver.saveStudent(student);
        return true;
    }
}