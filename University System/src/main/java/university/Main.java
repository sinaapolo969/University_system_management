package university;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    /*
    ادیت اینفو برای پرفسور زده شد
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
        System.out.println("1- STUDENT \n2- PROFESSOR \n3- MANAGER \n4- quit");
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
        System.out.println("1- add term \n2- add faculty \n3- create course");
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
                try
                {
                    FacultyInfo.get(0).createCourse();
                }
                catch (Exception error)
                {
                    System.out.println("there is not any faculty yet! please make a faculty and try again!");
                    System.out.println("press any key for come back to manager dashboard!");
                    String input = in.next();
                    if (!input.isEmpty())
                    {
                        ManagerDashboard();
                    }
                    else
                    {
                        ManagerDashboard();
                    }
                }

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
        System.out.println("1- sign in \n2- log in \n3- back to menu");
        System.out.println("please enter the your command number: ");
        String number2 = in.next();
        if (number2.equals("1"))
        {
            Professor professor = ProfessorSignIn();
        }
        else if (number2.equals("2"))
        {
            Professor professor = professorLogin();
            System.out.println("name:  " + professor.getProfessorName() + "  " + "ID:  " +
                    professor.getProfessorID());
            System.out.println();
            System.out.println("1- set score \n2- information \n3- edit info\n4- back to menu");
            String command = in.next();
            if (command.equals("1"))
            {
                FacultyInfo.get(0).setScore(professor);
            }
            else if (command.equals("2"))
            {
                displayProfessorInfo(professor);
            }
            else if (command.equals("3"))
            {
                editProfessorInfo(professor);
            }
            else if (command.equals("4"))
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
        System.out.println("1- sign in \n2- log in \n3- back to menu");
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
            System.out.println("1- add course \n2- information  \n3- edit info \n4- back to menu");
            String number3 = in.next();
            if (number3.equals("1"))
            {
                FacultyInfo.get(0).addCourse(student);
            }
            else if (number3.equals("2"))
            {
                System.out.println("1- personal information \n2- term info");
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
                        System.out.println("press any key for back to student dashboard");
                        String innerCommand = in.next();
                        if (!innerCommand.isEmpty())
                        {
                            StudentDashboard();
                        }
                        break;
                    case "2":
                        System.out.println("1- class schedule \n2- term average");
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

    public static void displayProfessorInfo(Professor professor)
    {
        System.out.println("<<PROFESSOR INFORMATION DASHBOARD>>");
        System.out.println("name:  " + professor.getProfessorName() + "\n ID:  " + professor.getProfessorID()
        + "\n faculty name:  " + professor.getFacultyName() + "\n department:  " + professor.getDepartment());
        System.out.println();
        System.out.println("<<LIST OF CLASSES>>");
        int i = 1;
        for (Classroom classroom : professor.classes)
        {
            System.out.println(i + "class name:  " + classroom.getCourse().getCourseName()
                    + "  class ID: " + classroom.getClassID());
        }
    }

    public static void editProfessorInfo(Professor professor)
    {
        displayProfessorInfo(professor);
        System.out.println("\n");
        System.out.println("<<EDIT PROFESSOR INFORMATION>>");
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
        professor.setProfessorName(name);
        professor.setFacultyName(facultyName);
        professor.setDepartment(department);
        faculty1.professorInfo.replace(professor.getProfessorID(), professor);
        System.out.println("information was successfully edited!");
        System.out.println("press any key for back to professor dashboard");
        String input = in.next();
        if (!input.isEmpty())
        {
            ProfessorDashboard();
        }
    }

}

