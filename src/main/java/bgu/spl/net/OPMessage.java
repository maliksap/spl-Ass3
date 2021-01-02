package bgu.spl.net;

public interface OPMessage {

    OPMessage react (String s);

    int getOpCode();
    String getLoggedInUser();

}