package bgu.spl.net;

public class OP3LoginRequestMessage extends OPMessageClass {
    private String username;
    private String password;
    public OP3LoginRequestMessage(int opCode, String username, String password) {
        super(opCode);
        this.username=username;
        this.password = password;
    }
}
