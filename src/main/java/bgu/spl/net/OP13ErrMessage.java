package bgu.spl.net;

public class OP13ErrMessage implements OPMessage {
    private int opcode;
    private int otherOp;
    private String loggedInUser;

    public OP13ErrMessage(int opCode, int otherOp) {
        this.opcode=opCode;
        this.otherOp=otherOp;
        this.loggedInUser=null;
    }

    @Override
    public String toString() {
        return "ERROR " + otherOp;
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
