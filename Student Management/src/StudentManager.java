public class StudentManager 
{
    public boolean createStudent(Student student)
    {
        StudentFileHandler saver= new StudentFileHandler();
        saver.saveStudent(student);
        return true;
    }
    public void findStudent(int id)
    {
        StudentFileHandler searcher= new StudentFileHandler();
        searcher.searchStudent(id);
    }
}