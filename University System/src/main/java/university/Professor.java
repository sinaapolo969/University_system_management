package university;

import java.util.ArrayList;
import java.util.HashMap;

public class Professor extends Faculty
{
    public static int numberOfProfessors = 0;
    private String professorName;
    private String department;
    private final String professorID;
    private String facultyName;
    protected ArrayList<Classroom> classes = new ArrayList<>();


    public Professor()
    {
        numberOfProfessors++;
        professorID = String.valueOf(numberOfProfessors) + "p";
    }
    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName)
    {
        this.facultyName = facultyName;
    }

    public String getProfessorName()
    {
        return professorName;
    }

    public void setProfessorName(String professorName)
    {
        this.professorName = professorName;
    }

    public String getProfessorID()
    {
        return professorID;
    }


}

