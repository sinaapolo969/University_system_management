package university;

import java.util.ArrayList;
import java.util.Arrays;
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
        try
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
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("The university has not yet defined the semester\nyou can make" +
                    " a semester in Manager -> add term");
            String input = messagePrinter();
            if (!input.isEmpty())
            {
                StudentDashboard();
            }
        }
       return null;
    }


    public static Professor ProfessorSignIn()
    {
        try
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
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("The university has not yet defined the semester\nyou can make" +
                    " a semester in Manager -> add term");
            String input = messagePrinter();
            if (!input.isEmpty())
            {
                StudentDashboard();
            }
        }
        return null;
    }

    public static Student studentLogin()
    {
        try
        {
            Faculty faculty1 = FacultyInfo.get(0);
            System.out.println("<<STUDENT LOGIN>>");
            System.out.println("please enter your ID: ");
            String studentID = in.next();
            return faculty1.studentInfo.get(studentID);
        }
        catch (Exception error)
        {
            System.out.println("The university has not yet defined the semester or faculty\nyou can make" +
                    " a semester in Manager -> add term or add faculty");
            String input = messagePrinter();
            if (!input.isEmpty())
            {
                StudentDashboard();
            }
        }
        return null;
    }

    public static Professor professorLogin()
    {
        try
        {
            System.out.println("<<PROFESSOR LOGIN>>");
            System.out.println("please enter your ID: ");
            String professorID = in.next();
            Faculty faculty1 = FacultyInfo.get(0);
            return faculty1.professorInfo.get(professorID);
        }
        catch (Exception error)
        {
            System.out.println("The university has not yet defined the semester or faculty\nyou can make" +
                    " a semester in Manager -> add term or add faculty");
            String input = messagePrinter();
            if (!input.isEmpty())
            {
                ProfessorDashboard();
            }
        }
        return null;
    }

    public static void ManagerDashboard()
    {
        System.out.println("<<MANAGER DASHBOARD>>");
        System.out.println("1- add term \n2- add faculty \n3- create course");
        String command = in.next();
        switch (command)
        {
            case "1":
                Amouzesh term = new Amouzesh();
                AmouzeshInfo.add(term);
                break;
            case "2":
                try
                {
                    System.out.println("please enter name of the faculty: ");
                    String facultyName = in.next();
                    Faculty faculty = AmouzeshInfo.get(0).createFaculty(facultyName);
                    FacultyInfo.add(faculty);
                }
                catch (Exception e)
                {
                    System.out.println("The university has not yet defined the semester\nyou can make" +
                            " a semester in Manager -> add term");
                    String input = messagePrinter();
                    if (!input.isEmpty())
                    {
                        ManagerDashboard();
                    }
                }

                break;
            case "3":
                try
                {
                    FacultyInfo.get(0).createCourse();
                }
                catch (IndexOutOfBoundsException error)
                {
                    System.out.println("there is not any faculty yet! please make a faculty and try again!");
                    System.out.println("press any key for back to dashboard");
                    String input = in.next();
                    if (input.isEmpty())
                    {
                        ManagerDashboard();
                    }
                }
                break;
            default:
                System.out.println("press any key for back to dashboard");
                String input = in.next();
                if (input.isEmpty())
                {
                    ManagerDashboard();
                }
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
            try
            {
                Professor professor = professorLogin();
                System.out.println("name:  " + professor.getProfessorName() + "\nID:  " +
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
                else
                {
                    String input = messagePrinter();
                    if (input.isEmpty())
                    {
                        ProfessorDashboard();
                    }
                }
            }
            catch (Exception error)
            {
                System.err.println("Invalid username(ID)! please try again!");
                ProfessorDashboard();
            }
        }
        else if (number2.equals("3"))
        {
            menu();
        }
        else
        {
            String input = messagePrinter();
            if (input.isEmpty())
            {
                ProfessorDashboard();
            }
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
            try
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
                    System.out.println("1- personal information \n2- term info and average");
                    System.out.println("please enter the number: ");
                    String command = in.next();
                    switch (command)
                    {
                        case "1":
                            displayStudentPersonalInfo(student);
                            break;
                        case "2":
                            displayStudentAcademicalInfo(student);
                    }
                }
                else if (number3.equals("3"))
                {
                    editStudentInfo(student);
                    menu();
                }
                else if (number3.equals("4"))
                {
                    menu();
                }
                else
                {
                    String input = messagePrinter();
                    if (input.isEmpty())
                    {
                        StudentDashboard();
                    }
                }
            }
            catch (Exception error)
            {
                System.err.println("invalid username(ID)! please try again!");
                String input = messagePrinter();
                if (!input.isEmpty())
                {
                    StudentDashboard();
                }
            }
        }
        else if (number2.equals("3"))
        {
            menu();
        }
        else
        {
            String input = messagePrinter();
            if (input.isEmpty())
            {
                StudentDashboard();
            }
        }
        menu();
    }

    public static void displayProfessorInfo(Professor professor)
    {
        System.out.println("<<PROFESSOR INFORMATION DASHBOARD>>");
        System.out.println("name:  " + professor.getProfessorName() + "\nID:  " + professor.getProfessorID()
        + "\nfaculty name:  " + professor.getFacultyName() + "\ndepartment:  " + professor.getDepartment());
        System.out.println();
        System.out.println("<<LIST OF CLASSES>>");
        int i = 1;
        for (Classroom classroom : professor.classes)
        {
            System.out.println(i + "- class name:  " + classroom.getCourse().getCourseName()
                    + "\nclass ID: " + classroom.getClassID());
        }
        System.out.println("\n");
        messagePrinter();
    }

    public static void displayStudentPersonalInfo(Student student)
    {
        System.out.println("<<STUDENT PERSONAL INFORMATION DASHBOARD>>");
        System.out.println("name:  " + student.getName() + "\nID:  " + student.getID()
                + "\n entrance year: " + student.getEntranceYear() + "\nfaculty name:  " + student.getFaculty()
                + "\n department: " + student.getDepartment());
        String input = messagePrinter();
        if (input.isEmpty())
        {
            StudentDashboard();
        }
    }

    public static void displayStudentAcademicalInfo(Student student)
    {
        System.out.println("<<COURSE INFORMATION DASHBOARD>>");
        int i = 1;
        for (Course course: student.courses)
        {
            System.out.println(i + "_ " + "course name:  " + course.getCourseName() + "  course ID:  " +
                    course.getCourseID() + "  course unit:  " + course.getUnit());
            i++;
        }
        System.out.println("\n");
        System.out.println("<<TERM INFORMATION AND AVERAGE>>");
        Faculty faculty = FacultyInfo.get(0);
        for (String name : student.scores.keySet())
        {
            System.out.println("course name:  " + faculty.courseInfo.get(name)
                    + "  score: " + student.scores.get(name));
        }
        System.out.println("Average: " + student.getTermAvg());
        String input = messagePrinter();
        if (input.isEmpty())
        {
            StudentDashboard();
        }

    }

    public static void editStudentInfo(Student student)
    {
        displayStudentPersonalInfo(student);
        System.out.println("\n");
        System.out.println("<<EDIT STUDENT INFORMATION DASHBOARD>>");
        System.out.println();
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
        student.setName(name);
        student.setFaculty(facultyName);
        student.setDepartment(department);
        faculty1.studentInfo.replace(student.getID(), student);
        System.out.println("information was successfully edited!");
        String input = messagePrinter();
        if (input.isEmpty())
        {
            StudentDashboard();
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
        String input = messagePrinter();
        if (input.isEmpty())
        {
            ProfessorDashboard();
        }
    }
    
    public static String messagePrinter()
    {
        System.out.println("press any key for back to dashboard");
        String input = in.next();
        return input;
    }

}

