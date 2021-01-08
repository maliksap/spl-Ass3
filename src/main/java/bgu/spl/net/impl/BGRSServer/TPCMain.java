package bgu.spl.net.impl.BGRSServer;

import bgu.spl.net.Database;
import bgu.spl.net.OPMessageEncoderDecoder;
import bgu.spl.net.OPMessageProtocol;
import bgu.spl.net.srv.BaseServer;
import bgu.spl.net.srv.Reactor;
import bgu.spl.net.srv.Server;


public class TPCMain {

    public static void main(String[] args) {
        BaseServer server = (BaseServer) Server.threadPerClient(
                7777,  //port
                () -> new OPMessageProtocol(),
                () -> new OPMessageEncoderDecoder());
        Database.getInstance().initialize("Courses.txt"); //TODO: change back to args[0]??
        server.serve();
    }

    }