package university;

import java.util.Scanner;

public class Main
{
    static Amouzesh term1 = new Amouzesh();
    static Faculty faculty1 = term1.createFaculty("math");
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args)
    {

        System.out.println(faculty1.getFacultyName());
        menu();
//        Professor professor = ProfessorSignIn();
//        faculty1.createCourse();

//        faculty1.addCourse(student);
//        faculty1.setScore(professor);
//        System.out.println(student.getTermAvg());
    }

    public static void menu()
    {
        System.out.println("<<MAIN MENU>>");
        System.out.println("1- STUDENT \n 2- PROFESSOR \n 3- MANAGER");
        System.out.println("please choose your field(enter the number): ");
        String  number = in.next();
        switch (number)
        {
            case "1":
                System.out.println("<<STUDENT DASHBOARD>>");
                System.out.println("1- sign in \n 2- log in");
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
                    System.out.println("1- add course \n 2- edit info");
                    String number3 = in.next();
                    if (number3.equals("1"))
                    {
                        faculty1.addCourse(student);
                    }
                    else if (number3.equals("2"))
                    {

                    }
                }
                menu();
                break;
            case "2":
                System.out.println("<<PROFESSOR DASHBOARD>>");
                System.out.println("1- sign in \n 2- log in");
                System.out.println("please enter the your command number: ");
                number2= in.next();
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
                    System.out.println("1- set score \n 2- edit info");
                    String command = in.next();
                    if (command.equals("1"))
                    {
                        faculty1.setScore(professor);
                    }
                    else if (command.equals("2"))
                    {

                    }
                }
                menu();
                break;
            case "3":
                faculty1.createCourse();
                menu();
                break;
            case "EXIT":
                System.exit(0);
        }
    }

    public static Student studentSignIn()
    {
        System.out.println("please enter your name: ");
        String name = in.next();
        System.out.println("faculties: ");
        int i = 1;
        for (Faculty faculty : term1.facultyInfo.values())
        {
            System.out.println(i + "- " + faculty.getFacultyName());
            i++;
        }
        System.out.println("please choose the faculty: ");
        String facultyName = in.next();
        System.out.println("please enter your department: ");
        String department = in.next();
        return faculty1.createStudentAccount(name, facultyName, department);
    }

    public static void addCourse(Student student)
    {
        student.addCourse(student);
    }

    public static Professor ProfessorSignIn()
    {
        System.out.println("please enter your name: ");
        String name = in.next();
        System.out.println("faculties: ");
        int i = 1;
        for (Faculty faculty : term1.facultyInfo.values())
        {
            System.out.println(i + "- " + faculty.getFacultyName());
            i++;
        }
        System.out.println("please choose the faculty: ");
        String facultyName = in.next();
        System.out.println("please enter your department: ");
        String department = in.next();
        return faculty1.createProfessorAccount(name, facultyName, department);
    }

    public static Student studentLogin()
    {
        System.out.println("please enter your ID: ");
        String studentID = in.next();
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
        System.out.println("please enter your ID: ");
        String professorID = in.next();
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

    public static void createCourse()
    {
        faculty1.createCourse();
    }

//    public void setScore(Professor professor)
//    {
//        professor.setScore();
//    }

}
