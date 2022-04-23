package university;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        catch (InputMismatchException error)
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
            System.out.println("course ID:  " + courseName.getCourseID() + "  course name:  "
                    + courseName.getCourseName() + "  course unit:  " + courseName.getUnit());
        }

        System.out.println("please enter the course ID: ");
        String courseID = in.next();
        if (student.courses.contains(courseInfo.get(courseID)))
        {
            System.out.println("you have already chosen this course!\nplease try another course!");
            System.out.println("press 1 if you want to choose another course and press any key if you want" +
                    " to back to dashboard");
            String input = in.next();
            if (input.equals("1"))
            {
                addCourse(student);
            }
            else
            {
                Main.StudentDashboard();
            }
        }
        //this if is for checking the total units of each student
        if (student.getTotalUnits() + courseInfo.get(courseID).getUnit() <= 20)
        {
            student.courses.add(courseInfo.get(courseID));
            student.addUnit(courseInfo.get(courseID).getUnit());
            addToClass(student, courseInfo.get(courseID));
            System.out.println("course successfully added!");
            System.out.println("press 1 if you want to choose another course and press any key if you want" +
                    " to back to dashboard");
            String input = in.next();
            if (input.equals("1"))
            {
                addCourse(student);
            }
            else
            {
                Main.StudentDashboard();
            }
        }
        else
        {
            System.out.println("Unauthorized amounts of units\n" +
                    "You only have 12 to 20 units to choose from each semester");
            System.out.println("press 1 if you want to choose another course and press any key if you want" +
                    " to back to dashboard");
            String input = in.next();
            if (input.equals("1"))
            {
                addCourse(student);
            }
            else
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
        Scanner input1 = new Scanner(System.in);
        if (professorInfo.isEmpty())
        {
            System.out.println("There are no professors in this faculty yet");
            course.editID();
            String input = Main.messagePrinter();
            if (!input.isEmpty())
            {
                Main.ManagerDashboard();
            }
        }
        else
        {
            try
            {
                Classroom newClass = new Classroom();
                newClass.setCourse(course);
                System.out.println("professors list: ");
                for (Professor professor : professorInfo.values())
                {
                    System.out.println("ID: " + professor.getProfessorID() + "  "
                            + "name: " + professor.getProfessorName() + "  "
                            + "department: " + professor.getDepartment() );
                }

                System.out.println("please enter the professor ID: ");
                String professorID = input1.next();
                newClass.setClassProfessor(professorID);
                Professor professor = professorInfo.get(professorID);
                professor.classes.add(newClass);
                classInfo.put(course.getCourseID(), newClass);
            }
            catch (Exception error)
            {
                System.out.println("invalid ID or username!\nplease try again");
                course.editID();
                String input = Main.messagePrinter();
                if (!input.isEmpty())
                {
                    Main.ManagerDashboard();
                }
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
                    Main.ProfessorDashboard();
                }
            }
            else
            {
                for (Student student : classroom.classMembers.values())
                {
                    System.out.println("student ID: " + student.getID() + "  " +
                            "student name: " + student.getName());
                }
                try
                {
                    System.out.println("please enter the student ID: ");
                    String studentID = in.next();
                    Student student = classroom.classMembers.get(studentID);
                    System.out.println("please enter the score: ");
                    String score = in.next();
                    Pattern pattern = Pattern.compile("([1][0-9]\\.[0-9]{2})|(1[0-9]\\.[0-9])|(20)|([0-9]\\.[0-9]{1,2})|([0-9])");
                    Matcher matcher = pattern.matcher(score);
                    if (matcher.matches())
                    {
                        student.scores.put(classroom.getCourse().getCourseID(), Double.parseDouble(score));
                        calculateAvg(student);
                        System.out.println("score successfully implemented");
                        System.out.println("for setting score again press 1 and press any key for back to " +
                                "dashboard!");
                        String input = in.next();
                        if (input.equals("1"))
                        {
                            setScore(professor);
                        }
                        else
                        {
                            Main.ProfessorDashboard();
                        }
                    }
                    else
                    {
                        System.out.println("the score must be a decimal number in form of XX.XX or X.X or XX.X" +
                                "or X.XX");
                        System.out.println("for setting score again press 1 and press any key for back to " +
                                "dashboard!");
                        String input = in.next();
                        if (input.equals("1"))
                        {
                            setScore(professor);
                        }
                        else
                        {
                            Main.ProfessorDashboard();
                        }
                        setScore(professor);
                    }
                }
                catch (NullPointerException error)
                {
                    System.out.println("there isnt any student with this ID!\nplease try again!");
                    String input = Main.messagePrinter();
                    if (!input.isEmpty())
                    {
                        Main.ProfessorDashboard();
                    }
                }
                catch (InputMismatchException error)
                {
                    System.out.println("the score must be a decimal number in form of XX.XX or X.X or XX.X" +
                            "or X.XX");
                    String input = Main.messagePrinter();
                    if (!input.isEmpty())
                    {
                        Main.ProfessorDashboard();
                    }
                }
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

    public void courseElimination(Student student)
    {
        if (student.isElimination())
        {
            if (student.courses.isEmpty())
            {
                System.out.println("No lessons have been defined for you yet");
                String input = Main.messagePrinter();
                if (!input.isEmpty())
                {
                    Main.StudentDashboard();
                }
            }
            else
            {
                try
                {
                    Scanner input = new Scanner(System.in);
                    System.out.println("<<COURSE ELIMINATION DASHBOARD>>");
                    System.out.println("courses list:");
                    for (Course course : student.courses)
                    {
                        System.out.println("course name:  " + course.getCourseName() + "  course ID:  "
                                + course.getCourseID() + "  course unit: " + course.getUnit());
                    }
                    System.out.println("please choose the course ID: ");
                    String courseID = input.next();
                    if (student.getTotalUnits() - courseInfo.get(courseID).getUnit() >= 12)
                    {
                        student.courses.remove(courseInfo.get(courseID));
                        student.setElimination(false);
                    }
                    else
                    {
                        System.out.println("The number of units to delete this lesson is more than allowed");
                    }
                }
                catch (NullPointerException | IndexOutOfBoundsException error)
                {
                    System.out.println("there isnt any course with this ID!");
                    String input = Main.messagePrinter();
                    if (!input.isEmpty())
                    {
                        Main.StudentDashboard();
                    }
                }
            }
        }
        else
        {
            System.out.println("you are not allowed to eliminate course!");
            String input = Main.messagePrinter();
            if (!input.isEmpty())
            {
                Main.StudentDashboard();
            }
        }

    }

}
