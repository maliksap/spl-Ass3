package bgu.spl.net;

public class OP9CheckIfRegMessage extends OPMessageClass {
    private int courseNum ;
    public OP9CheckIfRegMessage(int opCode, int courseNum) {
        super(opCode);
        this.courseNum=courseNum;
    }
}
