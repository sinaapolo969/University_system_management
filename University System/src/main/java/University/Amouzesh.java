package University;

import java.util.HashMap;

public class Amouzesh
{
    private HashMap<String, Student> studentInfo = new HashMap<>();
    private HashMap<String, Professor> professorInfo = new HashMap<>();
    protected HashMap<String, Course> courseInfo = new HashMap<>();
    protected HashMap<String, Classroom> classInfo = new HashMap<>();


    public void saveStudentInfo(Student std)
    {
        studentInfo.put(std.getID(), std);
    }

    public Student getStudentInfo(String id)
    {
        return studentInfo.get(id);
    }

    public void saveProfessorInfo(Professor professor)
    {
        professorInfo.put(professor.getProfessorID(), professor);
    }

    public void saveCourseInfo(Course course)
    {
        courseInfo.put(course.getCourseID(), course);
    }

    public Course getcourseInfo(String courseID)
    {
        return courseInfo.get(courseID);
    }

    public Professor getProfessorInfo(String professorID)
    {
        return professorInfo.get(professorID);
    }

    public void saveClassInfo(Classroom classroom)
    {
        classInfo.put(classroom.getCourse().getCourseID(), classroom);
    }
}
