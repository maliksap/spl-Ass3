package bgu.spl.net;

public class OP1AdminRegMessage extends OPMessageClass {
    private String username;
    private String password;
    public OP1AdminRegMessage(int opCode, String username, String password) {
        super(opCode);
        this.username=username;
        this.password = password;
    }
}
