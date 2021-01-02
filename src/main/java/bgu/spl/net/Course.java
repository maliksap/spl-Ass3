package bgu.spl.net;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Course {
    private String courseName;
    private int maxStudents;
    private Integer currStudents;
    private LinkedList<Integer> kdamCourses;
    private ConcurrentLinkedQueue<String> studsReg;

    public Course (String courseName, int maxStudents,LinkedList<Integer> kdamCourses, ConcurrentLinkedQueue<String> studsReg )
    {
        this.courseName=courseName;
        this.maxStudents=maxStudents;
        this.currStudents=0;
        this.kdamCourses=kdamCourses;
        this.studsReg=studsReg;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public Integer getCurrStudents() {
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

    public ConcurrentLinkedQueue<String> getStudsReg() {
        return studsReg;
    }
}
