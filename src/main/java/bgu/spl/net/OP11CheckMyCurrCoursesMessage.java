package bgu.spl.net;

import java.util.Arrays;

public class OP11CheckMyCurrCoursesMessage implements OPMessage {
    private int opcode;
    private String loggedInUser;

    public OP11CheckMyCurrCoursesMessage(int opCode) {
        this.opcode=opCode;
        this.loggedInUser=null;
    }

    @Override
    public OPMessage react(String s) {
        this.loggedInUser = s;
        Database database = Database.getInstance();
        if (loggedInUser == null) {
            return new OP13ErrMessage(13, 10);
        }
        if (database.getUsersInfo().get(loggedInUser).isAdmin()){
            return new OP13ErrMessage(13, 10);
        }
        User u = database.getUsersInfo().get(loggedInUser);
        String myCourses = Arrays.toString(u.getRegisteredCourses().toArray());
        return new OP12AckMessage(12,11,myCourses);
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
