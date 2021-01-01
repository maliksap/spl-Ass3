package bgu.spl.net;

public class OP8PrintStudStatMessage extends OPMessageClass {
    private String studUsername ;
    public OP8PrintStudStatMessage(int opCode, String studUsername) {
        super(opCode);
        this.studUsername=studUsername;
    }
}
