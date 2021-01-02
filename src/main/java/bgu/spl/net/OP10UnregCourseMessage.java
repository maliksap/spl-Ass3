package bgu.spl.net;

public class OP10UnregCourseMessage implements OPMessage {
    private int opcode;
    private int courseNum ;
    private String loggedInUser;
    public OP10UnregCourseMessage(int opCode, int courseNum) {
        this.opcode=opCode;
        this.courseNum=courseNum;
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
        if (!(database.getCoursesInfo().containsKey(courseNum))) {
            return new OP13ErrMessage(13, 10);
        }
        if(!(database.getCoursesInfo().get(courseNum).getStudsReg().contains(loggedInUser))){
            return new OP13ErrMessage(13, 10);
        }
        if(!(database.getUsersInfo().get(loggedInUser).getRegisteredCourses().contains(courseNum))){
            return new OP13ErrMessage(13, 10);
        }
        database.getUsersInfo().get(loggedInUser).getRegisteredCourses().remove(courseNum);
        database.getCoursesInfo().get(courseNum).getStudsReg().remove(loggedInUser);
        database.getCoursesInfo().get(courseNum).unRegStudent();

        return new OP12AckMessage(12,10,"");
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
