package bgu.spl.net;

public class OP10UnregCourseMessage extends OPMessageClass {
    private int courseNum ;
    public OP10UnregCourseMessage(int opCode, int courseNum) {
        super(opCode);
        this.courseNum=courseNum;
    }
}
