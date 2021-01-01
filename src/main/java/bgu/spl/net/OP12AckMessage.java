package bgu.spl.net;

public class OP12AckMessage extends OPMessageClass {
    private String optional;
    private int otherOp;
    public OP12AckMessage(int opCode, int otherOp, String optional ) {
        super(opCode);
        this.otherOp=otherOp;
        this.optional=optional;
    }

    @Override
    public String toString() {
        return "ACK " + otherOp +"\n" + optional;
    }
}
