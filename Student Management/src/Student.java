import java.io.Serializable;
public class Student implements Serializable
{
    private int id;
    private String name;
    private int age;
    private String course;

    public Student(int id, String name, int age, String course)
    {
        this.id=id;
        this.name=name;
        this.age=age;
        this.course=course;
    }
    
    //getters
    public int getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public int getAge()
    {
        return age;
    }
    public String course()
    {
        return course;
    }
}
