import java.util.LinkedList;

public class Course {
    private String courseName;
    private int maxStudents;
    private int currStudents;
    private LinkedList<Integer> kdamCourses;

    public Course (String courseName, int maxStudents,LinkedList<Integer> kdamCourses )
    {
        this.courseName=courseName;
        this.maxStudents=maxStudents;
        this.currStudents=0;
        this.kdamCourses=kdamCourses;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public int getCurrStudents() {
        return currStudents;
    }

    public LinkedList<Integer> getKdamCourses() {
        return kdamCourses;
    }

    public void regStudent ()
    {
        currStudents++;
    }
    public void unRegStudent ()
    {
        currStudents--;
    }
}
