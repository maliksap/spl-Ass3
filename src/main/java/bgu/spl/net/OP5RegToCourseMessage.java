package bgu.spl.net;

public class OP5RegToCourseMessage extends OPMessageClass {
    private int courseNum ;
    public OP5RegToCourseMessage(int opCode, int courseNum) {
        super(opCode);
        this.courseNum=courseNum;
    }
}
