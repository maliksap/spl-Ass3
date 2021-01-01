package bgu.spl.net;

public class OP6CheckKdamCourseMessage extends OPMessageClass {
    private int courseNum ;
    public OP6CheckKdamCourseMessage(int opCode, int courseNum) {
        super(opCode);
        this.courseNum=courseNum;
    }
}
