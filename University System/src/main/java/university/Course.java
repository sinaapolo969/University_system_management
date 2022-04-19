package university;

import java.util.ArrayList;
import java.util.HashMap;

public class Course extends Faculty
{
    private static int numberOfCourses = 0;
    private final String courseID;
    private int unit = 0;
    private String courseName;

    public Course()
    {
        numberOfCourses++;
        this.courseID = String.valueOf(numberOfCourses) + "c";
    }

    protected String getCourseID()
    {
        return courseID;
    }

    protected String getCourseName()
    {
        return courseName;
    }

    protected int getUnit()
    {
        return unit;
    }

    protected void setUnit(int unit)
    {
        this.unit = unit;
    }

    protected void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }


}

