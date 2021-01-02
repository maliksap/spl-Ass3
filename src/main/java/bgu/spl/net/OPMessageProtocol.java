package bgu.spl.net;

import bgu.spl.net.api.MessagingProtocol;

import java.time.LocalDateTime;

public class OPMessageProtocol implements MessagingProtocol<OPMessage> {

    private boolean shouldTerminate = false;
    private String loggedInUser = null;
    private Database database = Database.getInstance();

    @Override
    public OPMessage process(OPMessage msg) {
        if (loggedInUser != null && msg.getOpCode()==4){
            shouldTerminate = true;
        }
        OPMessage ans = msg.react(loggedInUser);
        this.loggedInUser= ans.getLoggedInUser();
        return ans;
    }



    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }



    public void setShouldTerminate(boolean shouldTerminate) {
        this.shouldTerminate = shouldTerminate;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
