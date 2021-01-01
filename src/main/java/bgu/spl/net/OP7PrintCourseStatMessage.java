package bgu.spl.net;

public class OP7PrintCourseStatMessage extends OPMessageClass {
    private int courseNum ;
    public OP7PrintCourseStatMessage(int opCode, int courseNum) {
        super(opCode);
        this.courseNum=courseNum;
    }
}
