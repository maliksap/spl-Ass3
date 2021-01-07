package bgu.spl.net;

import bgu.spl.net.srv.Reactor;

import java.sql.SQLOutput;
import java.time.LocalDateTime;



public class ReactorMain {

    public static void main(String[] args) {
        Reactor server = new Reactor(
                3,   // number of working threads
                Integer.decode(args[1]).intValue(),  //port
                () -> new OPMessageProtocol(),
                () -> new OPMessageEncoderDecoder());
        Database.getInstance().initialize(args[0]);
        server.serve();
    }

}