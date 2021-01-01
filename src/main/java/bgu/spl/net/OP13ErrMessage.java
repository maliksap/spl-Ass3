package bgu.spl.net;

public class OP13ErrMessage extends OPMessageClass {
    private int otherOp;
    public OP13ErrMessage(int opCode, int otherOp) {
        super(opCode);
        this.otherOp=otherOp;
    }

    @Override
    public String toString() {
        return "ERROR " + otherOp;
    }
}
