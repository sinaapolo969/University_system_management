package University;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Student extends Faculty
{
    Scanner in = new Scanner(System.in);
    private static int numberOfStudents = 0;
    private String name;
    private final String ID;
    private String department;
    private String faculty;
    private String entranceYear;
    private int totalUnits = 0;
    protected HashMap<Course, Double> scores = new HashMap<>();
    protected ArrayList<Course> courses = new ArrayList<>();

    public HashMap<Course, Double> getScores()
    {
        return scores;
    }

    public void setScores(double score, Course course)
    {
        scores.put(course, score);
    }

    public Student()
    {
        numberOfStudents++;
        setEntranceYear("400");
        ID = this.entranceYear + String.valueOf(numberOfStudents) + "s";
    }

    public void setEntranceYear(String entranceYear)
    {
        this.entranceYear = entranceYear;
    }

    public String getEntranceYear()
    {
        return entranceYear;
    }

    public String getFaculty()
    {
        return faculty;
    }

    public void setFaculty(String faculty)
    {
        this.faculty = faculty;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }


    public String getID()
    {
        return ID;
    }

    public void addUnit(int unit)
    {
        totalUnits += unit;
    }

    public int getTotalUnits()
    {
        return totalUnits;
    }

}
