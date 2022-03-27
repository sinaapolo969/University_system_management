package University;

import java.util.ArrayList;

public class Classroom extends Course
{
    private String classProfessor;
    private Course course;
    private ArrayList<Student> classMembers = new ArrayList<>();

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
        classMembers.add(student);
    }
}
