package university;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    /*
    اول اینکه باید با اکسپشن خطاهارو هندل کنیم
     دوم باید شرط بزاریم که درس تکراری برنداره
       سوم اینکه باید حالت هر بار جمع واحد ها هم نشون بده موقع انتخاب واحد
       موقع نمره گذاشتن استاد باید چک شه که کلاس خالی نباشه
       اپشن فامیلی هم که باید اد بشه
       در نهایت اینها اگ وقت اضاف امد فایل زده بشه
       گیت هم باید درست کنم
     */
    static ArrayList<Amouzesh> AmouzeshInfo = new ArrayList<>();
    static ArrayList<Faculty> FacultyInfo = new ArrayList<>();
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args)
    {
        menu();
    }

    public static void menu()
    {
        System.out.println("<<MAIN MENU>>");
        System.out.println("1- STUDENT \n 2- PROFESSOR \n 3- MANAGER \n 4- quit");
        System.out.println("please choose your field(enter the number): ");
        String  number = in.next();
        switch (number)
        {
            case "1":
                StudentDashboard();
                break;
            case "2":
                ProfessorDashboard();
                break;
            case "3":
                ManagerDashboard();
                break;
            case "4":
                System.exit(0);
                break;
            default:
                System.out.println("wrong input! \n please enter a number from the ordered numbers!");
                menu();
        }
    }

    public static Student studentSignIn()
    {
        System.out.println("<<STUDENT SIGN IN>>");
        System.out.println("please enter your name: ");
        String name = in.next();
        System.out.println("faculties: ");
        int i = 1;
        for (Faculty faculty : AmouzeshInfo.get(0).facultyInfo.values())
        {
            System.out.println(i + "- " + "faculty name:  " + faculty.getFacultyName() +
                    "  faculty ID:  " + faculty.facultyID);
            i++;
        }
        System.out.println("please enter the faculty ID: ");
        String facultyId = in.next();
        String facultyName;
        if (AmouzeshInfo.get(0).facultyInfo.containsKey(facultyId))
        {
            facultyName = AmouzeshInfo.get(0).facultyInfo.get(facultyId).getFacultyName();
        }
        else
        {
            facultyName = null;
            System.out.println("this faculty hasnt initialize yet! \n please choose the ID from the list!");
            studentSignIn();
        }
        System.out.println("please enter your department: ");
        String department = in.next();
        Faculty faculty1 = FacultyInfo.get(0);
        return faculty1.createStudentAccount(name, facultyName, department);
    }

    public static void addCourse(Student student)
    {
        student.addCourse(student);
    }

    public static Professor ProfessorSignIn()
    {
        System.out.println("<<PROFESSOR SIGN IN>>");
        System.out.println("please enter your name: ");
        String name = in.next();
        System.out.println("faculties: ");
        int i = 1;
        for (Faculty faculty : AmouzeshInfo.get(0).facultyInfo.values())
        {
            System.out.println(i + "- " + "faculty name:  " + faculty.getFacultyName() +
                    "  faculty ID:  " + faculty.facultyID);
            i++;
        }
        System.out.println("please enter the faculty ID: ");
        String facultyId = in.next();
        String facultyName;
        if (AmouzeshInfo.get(0).facultyInfo.containsKey(facultyId))
        {
            facultyName = AmouzeshInfo.get(0).facultyInfo.get(facultyId).getFacultyName();
        }
        else
        {
            facultyName = null;
            System.out.println("this faculty hasnt initialize yet! \n please choose the ID from the list!");
            studentSignIn();
        }
        System.out.println("please enter your department: ");
        String department = in.next();
        Faculty faculty1 = FacultyInfo.get(0);
        return faculty1.createProfessorAccount(name, facultyName, department);
    }

    public static Student studentLogin()
    {
        System.out.println("<<STUDENT LOGIN>>");
        System.out.println("please enter your ID: ");
        String studentID = in.next();
        Faculty faculty1 = FacultyInfo.get(0);
        try
        {
            return faculty1.studentInfo.get(studentID);
        }
        catch (NullPointerException error)
        {
            System.err.println("Invalid username(ID)! please try again!");
            studentLogin();
        }
        return null;
    }

    public static Professor professorLogin()
    {
        System.out.println("<<PROFESSOR LOGIN>>");
        System.out.println("please enter your ID: ");
        String professorID = in.next();
        Faculty faculty1 = FacultyInfo.get(0);
        try
        {
            return faculty1.professorInfo.get(professorID);
        }
        catch (NullPointerException error)
        {
            System.err.println("Invalid username(ID)! please try again!");
            professorLogin();
        }

        return null;
    }

    public static void ManagerDashboard()
    {
        System.out.println("<<MANAGER DASHBOARD");
        System.out.println("1- add term \n 2- add faculty \n 3- create course");
        String command = in.next();
        switch (command)
        {
            case "1":
                Amouzesh term = new Amouzesh();
                AmouzeshInfo.add(term);
                break;
            case "2":
                System.out.println("please enter name of the faculty: ");
                String facultyName = in.next();
                Faculty faculty = AmouzeshInfo.get(0).createFaculty(facultyName);
                FacultyInfo.add(faculty);
                break;
            case "3":
                FacultyInfo.get(0).createCourse();
                break;
            default:
                System.out.println("wrong input! \n please choose a number from the list");
                ManagerDashboard();

        }
        menu();
    }

    public static void ProfessorDashboard()
    {
        System.out.println("<<PROFESSOR DASHBOARD>>");
        System.out.println("1- sign in \n 2- log in \n 3- back to menu");
        System.out.println("please enter the your command number: ");
        String number2 = in.next();
        if (number2.equals("1"))
        {
            Professor professor = ProfessorSignIn();
        }
        else if (number2.equals("2"))
        {
            Professor professor = professorLogin();
            System.out.println("name: " + professor.getProfessorName() + " " + "ID: " +
                    professor.getProfessorID());
            System.out.println();
            System.out.println("1- set score \n 2- edit info \n 3- back to menu");
            String command = in.next();
            if (command.equals("1"))
            {
                FacultyInfo.get(0).setScore(professor);
            }
            else if (command.equals("2"))
            {

            }
            else if (command.equals("3"))
            {
                menu();
            }
        }
        else if (number2.equals("3"))
        {
            menu();
        }
        menu();
    }

    public static void StudentDashboard()
    {
        System.out.println("<<STUDENT DASHBOARD>>");
        System.out.println("1- sign in \n 2- log in \n 3- back to menu");
        System.out.println("please enter the your command number: ");
        String number2 = in.next();
        if (number2.equals("1"))
        {
            Student student = studentSignIn();
        }
        else if (number2.equals("2"))
        {
            Student student = studentLogin();
            System.out.println("name: " + student.getName() + " " + "ID: "
                    + student.getID());
            System.out.println();
            System.out.println("1- add course \n 2- information  \n 3- edit info \n 4- back to menu");
            String number3 = in.next();
            if (number3.equals("1"))
            {
                FacultyInfo.get(0).addCourse(student);
            }
            else if (number3.equals("2"))
            {
                System.out.println("1- personal information \n 2- term info");
                System.out.println("please enter the number: ");
                String command = in.next();
                switch (command)
                {
                    case "1":
                        System.out.println("name: " + student.getName()
                                + "ID: " + student.getID() + "\n"
                                + "Faculty " + student.getFaculty()
                                + "Department: " + student.getDepartment() +
                                "Entrance year: " + student.getEntranceYear());
                        System.out.println("for back to menu enter <<EXIT>>");
                        String innerCommand = in.next();
                        if (innerCommand.equals("EXIT"))
                        {
                            menu();
                        }
                        break;
                    case "2":
                        System.out.println("1- class schedule \n 2- term average");
                        System.out.println("please enter the number: ");
                        String innerCommand2 = in.next();
                        switch (innerCommand2)
                        {
                            case "1":
                                System.out.println("list of courses: ");
                                int i = 1;
                                for (Course course: student.courses)
                                {
                                    System.out.println(i + "_" +"course ID:  " + course.getCourseID() +
                                            "  course name:  " + course.getCourseName()
                                            + "  course unit:  " +
                                            course.getUnit());
                                    i++;
                                }
                                break;
                            case "2":
                                Faculty faculty = FacultyInfo.get(0);
                                for (String name : student.scores.keySet())
                                {
                                    System.out.println("course name:  " + faculty.courseInfo.get(name)
                                            + "  score: " + student.scores.get(name));
                                }
                                System.out.println("Average: " + student.getTermAvg());
                                break;
                            default:
                                throw new IllegalArgumentException("wrong input!");
                        }

                }
            }
            else if (number3.equals("3"))
            {
                menu();
            }
            else if (number3.equals("4"))
            {
                menu();
            }
            else
            {
                throw new IllegalArgumentException("wrong input!");
            }
        }
        else if (number2.equals("3"))
        {
            menu();
        }
        else
        {
            throw new IllegalArgumentException("wrong input!");

        }
        menu();
    }

}

