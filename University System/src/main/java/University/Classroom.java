package university;


import java.util.HashMap;

public class Classroom extends Faculty
{
    private static int numberOfClasses = 0;
    private String classProfessor;
    private final String classID;
    private Course course;
    protected HashMap<String, Student> classMembers = new HashMap<>();

    public Classroom()
    {
        numberOfClasses++;
        classID = String.valueOf(numberOfClasses) + "r";
    }

    public String getClassID()
    {
        return classID;
    }

    public String getClassProfessor()
    {
        return classProfessor;
    }

    public void setClassProfessor(String classProfessor)
    {
        this.classProfessor = classProfessor;
    }

    public Course getCourse()
    {
        return course;
    }

    public void setCourse(Course course)
    {
        this.course = course;
    }

    protected void addStudent(Student student)
    {
        classMembers.put(student.getID(), student);
    }

}
