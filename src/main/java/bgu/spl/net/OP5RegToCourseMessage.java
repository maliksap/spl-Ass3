package bgu.spl.net;

public class OP5RegToCourseMessage implements OPMessage {
    private int opcode;
    private int courseNum ;
    private String loggedInUser;
    public OP5RegToCourseMessage(int opCode, int courseNum) {
        this.opcode=opCode;
        this.courseNum=courseNum;
        this.loggedInUser = null;
    }

    @Override
    public OPMessage react(String s) {
        this.loggedInUser = s;
        Database database = Database.getInstance();
        if (loggedInUser == null) {
            return new OP13ErrMessage(13, 5);
        }
        if (database.getUsersInfo().get(loggedInUser).isAdmin()) {
            return new OP13ErrMessage(13, 5);
        }
        if (database.getCoursesInfo().containsKey(courseNum)) {
            return new OP13ErrMessage(13, 5);
        }
        if (database.getUsersInfo().get(loggedInUser).getRegisteredCourses().contains(courseNum)
        || database.getCoursesInfo().get(courseNum).getStudsReg().contains(loggedInUser)) {
            return new OP13ErrMessage(13, 5);
        }
        for (Integer courseKdam : database.getCoursesInfo().get(courseNum).getKdamCourses()) {
            if (!(database.getUsersInfo().get(loggedInUser).getRegisteredCourses().contains(courseKdam))) {
                return new OP13ErrMessage(13, 5);
            }
        }
        synchronized (database.getCoursesInfo().get(courseNum).getCurrStudents()) {
            if (database.getCoursesInfo().get(courseNum).getCurrStudents() >= database.getCoursesInfo().get(courseNum).getCurrStudents()) {
                return new OP13ErrMessage(13, 5);
            }
            database.getUsersInfo().get(loggedInUser).getRegisteredCourses().add(courseNum);
            database.getCoursesInfo().get(courseNum).getStudsReg().add(loggedInUser);
            database.getCoursesInfo().get(courseNum).regStudent();
        }
        return new OP12AckMessage(12, 5, "");
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
