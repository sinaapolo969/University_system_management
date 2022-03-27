package University;

import java.util.HashMap;
import java.util.Scanner;

public class Faculty extends Amouzesh
{
    private static int numberOfFaculties = 0;
    protected String facultyID;
    private String facultyName;
    HashMap<String, Classroom> classes = new HashMap<>();
    Scanner in = new Scanner(System.in);

    public Faculty()
    {

    }

    public Faculty(String facultyName)
    {
        numberOfFaculties++;
        this.facultyName = facultyName;
        facultyID = String.valueOf(numberOfFaculties) + "f";
    }

    public String getFacultyName()
    {
        return facultyName;
    }

    public void createCourse()
    {
        Course newCourse = new Course();
        System.out.println("please enter name of the course: ");
        String courseName = in.next();
        newCourse.setCourseName(courseName);
        System.out.println("please enter the unit of the course: ");
        int unit = in.nextInt();
        newCourse.setUnit(unit);
        saveCourseInfo(newCourse);
        createClass(newCourse);
    }

    public Student createStudentAccount()
    {
        Student student = new Student();
        System.out.println("please enter your name: ");
        String name = in.next();
        student.setName(name);
        System.out.println("please enter your faculty name: ");
        String facultyName = in.next();
        student.setFaculty(facultyName);
        System.out.println("please enter your department: ");
        String department = in.next();
        student.setDepartment(department);
        saveStudentInfo(student);
        return student;
    }

    public void addCourse(Student student)
    {
        System.out.println("courses list: ");
        for (Course courseName: courseInfo.values())
        {
            System.out.println(courseName.getCourseID() + " " + courseName.getCourseName());
        }

        System.out.println("please enter the course ID: ");
        String courseID = in.next();
        //this if is for checking the total units of each student
        if (student.getTotalUnits() + getcourseInfo(courseID).getUnit() <= 20)
        {
            student.courses.add(getcourseInfo(courseID));
            student.addUnit(getcourseInfo(courseID).getUnit());
            addToClass(student, courseInfo.get(courseID));
        }
        else
        {
            System.out.println("Unauthorized amounts of units");
        }

    }

    public void createProfessorAccount()
    {
        Professor professor = new Professor();
        System.out.println("please enter your name: ");
        String name = in.next();
        professor.setProfessorName(name);
        System.out.println("please enter your faculty name: ");
        String facultyName = in.next();
        professor.setFacultyName(facultyName);
        System.out.println("please enter your department: ");
        String department = in.next();
        professor.setDepartment(department);
        saveProfessorInfo(professor);
    }

    private void createClass(Course course)
    {
        Classroom newClass = new Classroom();
        newClass.setCourse(course);
        System.out.println("please enter the professor ID: ");
        String professorID = in.next();
        newClass.setClassProfessor(professorID);
    }

    private void addToClass(Student student, Course course)
    {
        Classroom classroom = classInfo.get(course.getCourseID());
        classroom.addStudent(student);
    }

}
