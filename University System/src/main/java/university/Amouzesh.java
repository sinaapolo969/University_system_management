package university;

import java.util.HashMap;

public class Amouzesh
{
    protected HashMap<String, Faculty> facultyInfo = new HashMap<>();

    public Faculty createFaculty(String facultyName)
    {
        Faculty faculty = new Faculty(facultyName);
        facultyInfo.put(faculty.facultyID, faculty);
        return faculty;
    }

    public Faculty getFacultyInfo(String facultyID)
    {
        return facultyInfo.get(facultyID);
    }
}

