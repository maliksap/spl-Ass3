package bgu.spl.net;

public class OP2StudentRegMessage extends OPMessageClass {
    private String username;
    private String password;
    public OP2StudentRegMessage(int opCode, String username, String password) {
        super(opCode);
        this.username=username;
        this.password = password;
    }
}
