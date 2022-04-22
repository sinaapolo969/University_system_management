package university;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Faculty extends Amouzesh
{
    private static int numberOfFaculties = 0;
    protected String facultyID;
    private String facultyName;
    protected HashMap<String, Student> studentInfo = new HashMap<>();
    protected HashMap<String, Professor> professorInfo = new HashMap<>();
    protected HashMap<String, Course> courseInfo = new HashMap<>();
    protected HashMap<String, Classroom> classInfo = new HashMap<>();
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
        Scanner input1 = new Scanner(System.in);
        try
        {
            Course newCourse = new Course();
            System.out.println("please enter name of the course: ");
            String courseName = input1.nextLine();
            newCourse.setCourseName(courseName);
            System.out.println("please enter the unit of the course: ");
            int unit = Integer.parseInt(input1.nextLine());
            newCourse.setUnit(unit);
            courseInfo.put(newCourse.getCourseID(), newCourse);
            createClass(newCourse);
        }
        catch (Exception error)
        {
            System.out.println("Your course unit must be an integer!");
            String input = Main.messagePrinter();
            if (!input.isEmpty())
            {
                Main.ManagerDashboard();
            }
        }
    }

    public Student createStudentAccount(String studentName, String facultyName, String department, String entranceYear)
    {
        Student student = new Student();
        student.setName(studentName);
        student.setFaculty(facultyName);
        student.setDepartment(department);
        student.setEntranceYear(entranceYear);
        studentInfo.put(student.getID(), student);
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
        if (student.getTotalUnits() + courseInfo.get(courseID).getUnit() <= 20)
        {
            student.courses.add(courseInfo.get(courseID));
            student.addUnit(courseInfo.get(courseID).getUnit());
            addToClass(student, courseInfo.get(courseID));
        }
        else
        {
            System.out.println("Unauthorized amounts of units\n" +
                    "You only have 12 to 20 units to choose from each semester");
            String input = Main.messagePrinter();
            if (!input.isEmpty())
            {
                Main.StudentDashboard();
            }
        }

    }

    public Professor createProfessorAccount(String professorName, String facultyName, String department)
    {
        Professor professor = new Professor();
        professor.setProfessorName(professorName);
        professor.setFacultyName(facultyName);
        professor.setDepartment(department);
        professorInfo.put(professor.getProfessorID(), professor);
        return professor;
    }

    private void createClass(Course course)
    {
        try
        {
            Classroom newClass = new Classroom();
            newClass.setCourse(course);
            System.out.println("professors list: ");
            if (professorInfo.isEmpty())
            {
                System.out.println("There are no professors in this faculty yet");
                String input = Main.messagePrinter();
                if (!input.isEmpty())
                {
                    Main.ManagerDashboard();
                }
            }
            else
            {
                for (Professor professor : professorInfo.values())
                {
                    System.out.println("ID: " + professor.getProfessorID() + "  "
                            + "name: " + professor.getProfessorName() + "  "
                            + "department: " + professor.getDepartment() );
                }

                System.out.println("please enter the professor ID: ");
                String professorID = in.next();
                newClass.setClassProfessor(professorID);
                Professor professor = professorInfo.get(professorID);
                professor.classes.add(newClass);
                classInfo.put(course.getCourseID(), newClass);
            }
        }
        catch (Exception error)
        {
            System.out.println("invalid ID or username!\nplease try again");
            String input = Main.messagePrinter();
            if (!input.isEmpty())
            {
                Main.ManagerDashboard();
            }
        }
    }

    public void setScore(Professor professor)
    {
        System.out.println("list of classes: ");

        if (professor.classes.isEmpty())
        {
            System.out.println("A class has not been defined for you yet");
            String input = Main.messagePrinter();
            if (!input.isEmpty())
            {
                Main.ProfessorDashboard();
            }
        }
        else
        {
            for (Classroom classroom : professor.classes)
            {
                System.out.println("class ID: " + classroom.getCourse().getCourseID() + "  "
                        + "course name: " + classroom.getCourse().getCourseName());
            }
            System.out.println("please enter the class ID: ");
            String classID = in.next();
            Classroom classroom = classInfo.get(classID);

            if (classroom.classMembers.isEmpty())
            {
                System.out.println("No students have enrolled in this course yet");
                String input = Main.messagePrinter();
                if (!input.isEmpty())
                {
                    Main.ManagerDashboard();
                }
            }
            else
            {
                for (Student student : classroom.classMembers.values())
                {
                    System.out.println("student ID: " + student.getID() + "  " +
                            "student name: " + student.getName());
                }

                System.out.println("please enter the student ID: ");
                String studentID = in.next();
                Student student = classroom.classMembers.get(studentID);

                System.out.println("please enter the score: ");
                double score = Double.parseDouble(in.nextLine());
                student.scores.put(classroom.getCourse().getCourseID(), score);
                calculateAvg(student);
            }
        }
    }

    private void calculateAvg(Student student)
    {
        double avg = 0;
        for (double score : student.scores.values())
        {
            avg += score;
        }
        avg /= student.scores.size();
        student.setTermAvg(avg);

    }

    private void addToClass(Student student, Course course)
    {
        Classroom classroom = classInfo.get(course.getCourseID());
        classroom.addStudent(student);
        classInfo.put(classroom.getCourse().getCourseID(), classroom);
    }

}
