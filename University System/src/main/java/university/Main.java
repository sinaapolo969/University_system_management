package university;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    /*
    رجکس برای ورود نمره باید وارد شود
     */
    static ArrayList<Amouzesh> AmouzeshInfo = new ArrayList<>();
    static ArrayList<Faculty> FacultyInfo = new ArrayList<>();
    static Scanner in = new Scanner(System.in);
    static boolean facultyExistence = false;
    static boolean semesterExistence = false;
    public static void main(String[] args)
    {
        tester();
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
        Scanner input = new Scanner(System.in);
        if (!semesterExistence)
        {
            System.out.println("The university has not yet defined the semester\nyou can make" +
                    " a semester in Manager -> add term");
            String input4 = messagePrinter();
            if (!input4.isEmpty())
            {
                StudentDashboard();
            }
        }
        else if (!facultyExistence)
        {
            System.out.println("The university has not yet defined the faculty\nyou can make" +
                    " a faculty in Manager -> add faculty");
            String input1 = messagePrinter();
            if (!input1.isEmpty())
            {
                StudentDashboard();
            }
        }
        else
        {
            System.out.println("<<STUDENT SIGN IN>>");
            System.out.println("please enter your name: ");
            String name = input.nextLine();
            System.out.println("faculties: ");
            int i = 1;
            for (Faculty faculty : AmouzeshInfo.get(0).facultyInfo.values())
            {
                System.out.println(i + "- " + "faculty name:  " + faculty.getFacultyName() +
                        "  faculty ID:  " + faculty.facultyID);
                i++;
            }
            System.out.println("please enter the faculty ID: ");
            String facultyId = input.nextLine();
            String facultyName;
            if (AmouzeshInfo.get(0).facultyInfo.containsKey(facultyId))
            {
                facultyName = AmouzeshInfo.get(0).facultyInfo.get(facultyId).getFacultyName();
            }
            else
            {
                facultyName = null;
                System.out.println("this faculty hasnt initialize yet! \n please choose the ID from the list!");
                StudentDashboard();
            }
            System.out.println("please enter your department: ");
            String department = input.nextLine();
            System.out.println("please enter your entrance year: ");
            String entranceYear = in.next();
            Faculty faculty1 = FacultyInfo.get(0);
            return faculty1.createStudentAccount(name, facultyName, department, entranceYear);
        }
        return null;
    }


    public static Professor ProfessorSignIn()
    {
        Scanner input1 = new Scanner(System.in);
        if (!semesterExistence)
        {
            System.out.println("The university has not yet defined the semester\nyou can make" +
                    " a semester in Manager -> add term");
            String input4 = messagePrinter();
            if (!input4.isEmpty())
            {
                ProfessorDashboard();
            }
        }
        else if (!facultyExistence)
        {
            System.out.println("The university has not yet defined the faculty\nyou can make" +
                    " a faculty in Manager -> add faculty");
            String input = messagePrinter();
            if (!input.isEmpty())
            {
                ProfessorDashboard();
            }
        }
        else
        {
            System.out.println("<<PROFESSOR SIGN IN>>");
            System.out.println("please enter your name: ");
            String name = input1.nextLine();
            System.out.println("faculties: ");
            int i = 1;
            for (Faculty faculty : AmouzeshInfo.get(0).facultyInfo.values())
            {
                System.out.println(i + "- " + "faculty name:  " + faculty.getFacultyName() +
                        "  faculty ID:  " + faculty.facultyID);
                i++;
            }
            System.out.println("please enter the faculty ID: ");
            String facultyId = input1.nextLine();
            String facultyName;
            if (AmouzeshInfo.get(0).facultyInfo.containsKey(facultyId))
            {
                facultyName = AmouzeshInfo.get(0).facultyInfo.get(facultyId).getFacultyName();
            }
            else
            {
                facultyName = null;
                System.out.println("this faculty hasnt initialize yet! \n please choose the ID from the list!");
                ProfessorDashboard();
            }
            System.out.println("please enter your department: ");
            String department = input1.nextLine();
            Faculty faculty1 = FacultyInfo.get(0);
            return faculty1.createProfessorAccount(name, facultyName, department);
        }
        return null;
    }

    public static Student studentLogin()
    {
        if (!semesterExistence)
        {
            System.out.println("The university has not yet defined the semester\nyou can make" +
                    " a semester in Manager -> add term");
            String input4 = messagePrinter();
            if (!input4.isEmpty())
            {
                StudentDashboard();
            }
        }
        else if (!facultyExistence)
        {
            System.out.println("The university has not yet defined the faculty\nyou can make" +
                    " a faculty in Manager -> add faculty");
            String input = messagePrinter();
            if (!input.isEmpty())
            {
                StudentDashboard();
            }
        }
        else
        {
            Faculty faculty1 = FacultyInfo.get(0);
            System.out.println("<<STUDENT LOGIN>>");
            System.out.println("please enter your ID: ");
            String studentID = in.next();
            return faculty1.studentInfo.get(studentID);
        }
        return null;
    }

    public static Professor professorLogin()
    {
        if (!semesterExistence)
        {
            System.out.println("The university has not yet defined the semester\nyou can make" +
                    " a semester in Manager -> add term");
            String input4 = messagePrinter();
            if (!input4.isEmpty())
            {
                ProfessorDashboard();
            }
        }
        else if (!facultyExistence)
        {
            System.out.println("The university has not yet defined the faculty\nyou can make" +
                    " a faculty in Manager -> add faculty");
            String input = messagePrinter();
            if (!input.isEmpty())
            {
                ProfessorDashboard();
            }
        }
        else
        {
            try
            {
                System.out.println("<<PROFESSOR LOGIN>>");
                System.out.println("please enter your ID: ");
                String professorID = in.next();
                Faculty faculty1 = FacultyInfo.get(0);
                return faculty1.professorInfo.get(professorID);
            }
            catch (NullPointerException | IndexOutOfBoundsException error)
            {
                System.out.println("there isnt any professor account with this ID\nplease try again!");
                String input = messagePrinter();
                if (!input.isEmpty())
                {
                    ProfessorDashboard();
                }
            }
        }
        return null;
    }

    public static void ManagerDashboard()
    {
        Scanner input2 = new Scanner(System.in);
        System.out.println("<<MANAGER DASHBOARD>>");
        System.out.println("1- add term \n2- add faculty \n3- create course\n4- back to menu");
        String command = in.next();
        switch (command)
        {
            case "1":
                Amouzesh term = new Amouzesh();
                AmouzeshInfo.add(term);
                System.out.println("New semester successfully defined");
                semesterExistence = true;
                String input1 = messagePrinter();
                if (!input1.isEmpty())
                {
                    ManagerDashboard();
                }
                break;
            case "2":
                if (!semesterExistence)
                {
                    System.out.println("The university has not yet defined the semester\nyou can make" +
                            " a semester in Manager -> add term");
                    String input4 = messagePrinter();
                    if (!input4.isEmpty())
                    {
                        ManagerDashboard();
                    }
                }
                else
                {
                    System.out.println("please enter name of the faculty: ");
                    String facultyName = input2.nextLine();
                    Faculty faculty = AmouzeshInfo.get(0).createFaculty(facultyName);
                    FacultyInfo.add(faculty);
                    System.out.println("The new faculty was successfully defined");
                    facultyExistence = true;
                    String input = messagePrinter();
                    if (!input.isEmpty())
                    {
                        ManagerDashboard();
                    }
                }
                break;
            case "3":
                if (!semesterExistence)
                {
                    System.out.println("The university has not yet defined the semester\nyou can make" +
                            " a semester in Manager -> add term");
                    String input4 = messagePrinter();
                    if (!input4.isEmpty())
                    {
                        ManagerDashboard();
                    }
                }
                else if (!facultyExistence)
                {
                    System.out.println("The university has not yet defined the faculty\nyou can make" +
                            " a faculty in Manager -> add faculty");
                    String input = messagePrinter();
                    if (!input.isEmpty())
                    {
                        ManagerDashboard();
                    }
                }
                else
                {
                    FacultyInfo.get(0).createCourse();
                    System.out.println("course was successfully created!");
                    String input = messagePrinter();
                    if (input.isEmpty())
                    {
                        ManagerDashboard();
                    }
                }
                break;
            case "4":
                menu();
                break;
            default:
                System.out.println("wrong input!\npress any key for back to dashboard");
                String input3 = in.next();
                if (input3.isEmpty())
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
        switch (number2) {
            case "1":
                Professor professor1 = ProfessorSignIn();
                break;
            case "2":
                try
                {
                    Professor professor = professorLogin();
                    System.out.println("name:  " + professor.getProfessorName() + "\nID:  " +
                            professor.getProfessorID());
                    System.out.println();
                    System.out.println("1- set score \n2- information \n3- edit info\n4- back to menu");
                    String command = in.next();
                    switch (command)
                    {
                        case "1":
                             FacultyInfo.get(0).setScore(professor);
                             break;
                        case "2":
                            displayProfessorInfo(professor);
                            String input1 = messagePrinter();
                            if (!input1.isEmpty())
                            {
                                ProfessorDashboard();
                            }
                            break;
                        case "3":
                            editProfessorInfo(professor);
                            break;
                        case "4":
                            menu();
                            break;
                        default:
                            System.err.println("wrong input!");
                            String input = messagePrinter();
                            if (input.isEmpty())
                            {
                                ProfessorDashboard();
                            }
                    }
                }
                catch (IndexOutOfBoundsException | NullPointerException error)
                {
                    System.err.println("Invalid username(ID)! please try again!");
                    ProfessorDashboard();
                }
                break;
            case "3":
                menu();
                break;
            default:
                System.err.println("Wrong input!");
                String input = messagePrinter();
                if (input.isEmpty()) {
                    ProfessorDashboard();
                }
                break;
        }
        menu();
    }

    public static void StudentDashboard()
    {
        System.out.println("<<STUDENT DASHBOARD>>");
        System.out.println("1- sign in \n2- log in \n3- back to menu");
        System.out.println("please enter the your command number: ");
        String number2 = in.next();
        switch (number2)
        {
            case "1":
                Student student1 = studentSignIn();
                displayStudentPersonalInfo(student1);
                System.out.println("Your account was created successfully");
                String input = messagePrinter();
                if (!input.isEmpty())
                {
                    menu();
                }
                break;
            case "2":
                try
                {
                    Student student = studentLogin();
                    System.out.println("name: " + student.getName() + " " + "ID: "
                            + student.getID());
                    System.out.println();
                    System.out.println("1- add course \n2- information  \n3- edit info \n" +
                            "4- course elimination\n5- back to menu");
                    String number3 = in.next();
                    switch (number3) {
                        case "1":
                            try
                            {
                                FacultyInfo.get(0).addCourse(student);
                            }
                            catch (IndexOutOfBoundsException | NullPointerException error)
                            {
                                System.out.println("this course hasnt defined yet!\nfor make a course:\n" +
                                        "Manager -> create course");
                                String input1 = messagePrinter();
                                if (input1.isEmpty()) {
                                    StudentDashboard();
                                }
                            }
                            break;
                        case "2":
                            System.out.println("1- personal information \n2- term info and average");
                            System.out.println("please enter the number: ");
                            String command = in.next();
                            switch (command)
                            {
                                case "1":
                                    displayStudentPersonalInfo(student);
                                    String input1 = messagePrinter();
                                    if (input1.isEmpty())
                                    {
                                        StudentDashboard();
                                    }
                                    break;
                                case "2":
                                    displayStudentAcademicalInfo(student);
                                    String input2 = messagePrinter();
                                    if (input2.isEmpty())
                                    {
                                        StudentDashboard();
                                    }

                                    break;
                                default:
                                    System.err.println("wrong input!");
                                    String input3 = messagePrinter();
                                    if (!input3.isEmpty())
                                    {
                                        StudentDashboard();
                                    }
                            }
                            break;
                        case "3":
                            editStudentInfo(student);
                            menu();
                            break;
                        case "4":
                            FacultyInfo.get(0).courseElimination(student);
                            break;
                        case "5":
                            menu();
                            break;
                        default:
                            System.err.println("wrong input!");
                            String input2 = messagePrinter();
                            if (input2.isEmpty()) {
                                StudentDashboard();
                            }
                            break;
                    }
                }
                catch (NullPointerException error)
                {
                    System.err.println("invalid username(ID)! please try again!");
                    String input3 = messagePrinter();
                    if (!input3.isEmpty())
                    {
                        StudentDashboard();
                    }
                }
                break;
            case "3":
                menu();
                break;
            default:
                System.err.println("wrong input!");
                String input4 = messagePrinter();
                if (input4.isEmpty())
                {
                    StudentDashboard();
                }
                break;
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
    }

    public static void displayStudentPersonalInfo(Student student)
    {
        System.out.println("<<STUDENT PERSONAL INFORMATION DASHBOARD>>");
        System.out.println("name:  " + student.getName() + "\nID:  " + student.getID()
                + "\nentrance year: " + student.getEntranceYear() + "\nfaculty name:  " + student.getFaculty()
                + "\ndepartment: " + student.getDepartment());
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
    }

    public static void editStudentInfo(Student student)
    {
        Scanner input1 = new Scanner(System.in);
        displayStudentPersonalInfo(student);
        System.out.println("\n");
        System.out.println("<<EDIT STUDENT INFORMATION DASHBOARD>>");
        System.out.println();
        System.out.println("please enter your name: ");
        String name = in.nextLine();
        System.out.println("faculties: ");
        int i = 1;
        for (Faculty faculty : AmouzeshInfo.get(0).facultyInfo.values())
        {
            System.out.println(i + "- " + "faculty name:  " + faculty.getFacultyName() +
                    "  faculty ID:  " + faculty.facultyID);
            i++;
        }
        System.out.println("please enter the faculty ID: ");
        String facultyId = in.nextLine();
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
        String department = in.nextLine();
        System.out.println("please enter your entranceYear: ");
        String entranceYear = input1.nextLine();
        Faculty faculty1 = FacultyInfo.get(0);
        student.setName(name);
        student.setFaculty(facultyName);
        student.setDepartment(department);
        student.setEntranceYear(entranceYear);
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
        Scanner input1 = new Scanner(System.in);
        displayProfessorInfo(professor);
        System.out.println("\n");
        System.out.println("<<EDIT PROFESSOR INFORMATION>>");
        System.out.println("please enter your name: ");
        String name = input1.nextLine();
        System.out.println("faculties: ");
        int i = 1;
        for (Faculty faculty : AmouzeshInfo.get(0).facultyInfo.values())
        {
            System.out.println(i + "- " + "faculty name:  " + faculty.getFacultyName() +
                    "  faculty ID:  " + faculty.facultyID);
            i++;
        }
        System.out.println("please enter the faculty ID: ");
        String facultyId = input1.nextLine();
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
        String department = input1.nextLine();
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

    public static void tester()
    {
        semesterExistence = true;
        facultyExistence = true;
        Amouzesh term = new Amouzesh();
        AmouzeshInfo.add(term);
        Faculty faculty = term.createFaculty("math");
        FacultyInfo.add(faculty);
        Professor professor = faculty.createProfessorAccount("sina", "math", "math");
        faculty.createCourse();
        Student student = faculty.createStudentAccount("pouya mirzaei", "math", "math", "400");
    }

}

