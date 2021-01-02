package bgu.spl.net;

public class OP12AckMessage implements OPMessage {
    private int opcode;
    private String optional;
    private short otherOp;
    private String loggedInUser;
    public OP12AckMessage(int opCode, short otherOp, String optional ) {
        this.opcode=opCode;
        this.otherOp=otherOp;
        this.optional=optional;
        this.loggedInUser=null;
    }

    @Override
    public String toString() {
        return "ACK " + otherOp +"\n" + optional;
    }

    @Override
    public OPMessage react(String s) {
        return null;
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
