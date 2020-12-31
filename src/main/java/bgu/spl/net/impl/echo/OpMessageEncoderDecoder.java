package bgu.spl.net.impl.echo;

import bgu.spl.net.api.MessageEncoderDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

public class OpMessageEncoderDecoder implements MessageEncoderDecoder<String> {

    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private int len = 0;
    private int opCounter=0;
    private int userInfoCounter=0;
    private HashMap<String,String> messageContent=new HashMap<String, String>();; //we added

    @Override
    public HashMap<String,String> decodeNextByte(byte nextByte) {
        //notice that the top 128 ascii characters have the same representation as their utf-8 counterparts
        //this allow us to do the following comparison

        if (opCounter==2)
        {
            messageContent.putIfAbsent("op",popString());
        }

        else if (opCounter > 2 && (messageContent.get("op").equals("1") || messageContent.get("op").equals("2") || messageContent.get("op").equals("3")))
        {
            if (nextByte == '0' && userInfoCounter==0) {
                messageContent.putIfAbsent("userName",popString());
                userInfoCounter++;
            }
            else if(nextByte == '0'&& userInfoCounter==1){
                messageContent.putIfAbsent("password",popString());
                return messageContent;
        }
            }
        else if(opCounter > 2 && (messageContent.get("op").equals("5") || messageContent.get("op").equals("6") || messageContent.get("op").equals("7"))|| messageContent.get("op").equals("9") || messageContent.get("op").equals("10")){
            if (len==2) {
                messageContent.putIfAbsent("courseNumber", popString());
                return messageContent;
            }
        }
        else if (opCounter > 2 && (messageContent.get("op").equals("8"))) {
            if (nextByte == '0') {
                messageContent.putIfAbsent("studentUsername", popString());
                return messageContent;
            }
        }
        else if (opCounter>2){
            return messageContent;
        }

//        if (nextByte == '\n') {
//            return popString();
//        }
        pushByte(nextByte);
        return null; //not a line yet
    }

    @Override
    public byte[] encode(String message) {
        return (message + "\n").getBytes(); //uses utf8 by default
    }

    private void pushByte(byte nextByte) {
        if (len >= bytes.length) {
            bytes = Arrays.copyOf(bytes, len * 2);
        }

        bytes[len++] = nextByte;
        opCounter++;
    }

    private String popString() {
        //notice that we explicitly requesting that the string will be decoded from UTF-8
        //this is not actually required as it is the default encoding in java.
        String result = new String(bytes, 0, len, StandardCharsets.UTF_8);
        len = 0;
        return result;
    }
}
