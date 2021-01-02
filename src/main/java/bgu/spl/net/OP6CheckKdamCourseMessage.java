package bgu.spl.net;

import java.util.Arrays;
import java.util.Vector;

public class OP6CheckKdamCourseMessage implements OPMessage {
    private int opcode;
    private int courseNum ;
    private String loggedInUser;
    public OP6CheckKdamCourseMessage(int opCode, int courseNum) {
        this.opcode=opCode;
        this.courseNum=courseNum;
        this.loggedInUser=null;
    }

    @Override
    public OPMessage react(String s) {
        this.loggedInUser = s;
        Database database = Database.getInstance();
        if (loggedInUser == null) {
            return new OP13ErrMessage(13, (short) 6);
        }
        if (database.getCoursesInfo().containsKey(courseNum)){
            return new OP13ErrMessage(13, (short) 6);
        }
        String kdamCurses = Arrays.toString(database.getCoursesInfo().get(courseNum).getKdamCourses().toArray());
        return new OP12AckMessage(12, (short) 6,kdamCurses);
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
