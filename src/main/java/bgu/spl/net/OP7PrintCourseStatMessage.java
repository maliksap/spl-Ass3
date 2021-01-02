package bgu.spl.net;

import java.util.Arrays;

public class OP7PrintCourseStatMessage implements OPMessage {
    private int opcode;
    private int courseNum ;
    private String loggedInUser;
    public OP7PrintCourseStatMessage(int opCode, int courseNum) {
        this.opcode=opCode;
        this.courseNum=courseNum;
        this.loggedInUser=null;
    }

    @Override
    public OPMessage react(String s) {
        this.loggedInUser = s;
        Database database = Database.getInstance();
        if (loggedInUser == null) {
            return new OP13ErrMessage(13, 7);
        }
        if (!(database.getUsersInfo().get(loggedInUser).isAdmin())){
            return new OP13ErrMessage(13, 7);
        }
        if (!(database.getCoursesInfo().containsKey(courseNum))){
            return new OP13ErrMessage(13, 7);
        }
        synchronized (database.getCoursesInfo().get(courseNum).getCurrStudents()) {
            Course c = database.getCoursesInfo().get(courseNum);
            String studReg = Arrays.toString(c.getStudsReg().toArray());
            String stat = "Course:(" + courseNum + ")" + c.getCourseName() + "\n"
                    + "Seats Available:" + c.getCurrStudents() + "/"
                    + c.getMaxStudents() + "\n" + "Student Registered:" + studReg;
            return new OP12AckMessage(12, 7, stat);
        }
    }

    @Override
    public int getOpCode() {
        return opcode;
    }

    @Override
    public String getLoggedInUser() {
        return loggedInUser;
    }
}
