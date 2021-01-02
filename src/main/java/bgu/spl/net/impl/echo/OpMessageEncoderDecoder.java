//package bgu.spl.net.impl.echo;
//
//import bgu.spl.net.*;
//import bgu.spl.net.api.MessageEncoderDecoder;
//import java.nio.charset.StandardCharsets;
//import java.util.Arrays;
//import java.util.HashMap;
//
//public class OpMessageEncoderDecoder implements MessageEncoderDecoder<OPMessageClass> {
//
//    private byte[] bytes = new byte[1 << 10]; //start with 1k
//    private int len = 0;
//    private int opCounter=0;
//    private int userInfoCounter=0;
//    private HashMap<String,String> info =new HashMap<String, String>();; //we added
//
//    @Override
//    public OPMessageClass decodeNextByte(byte nextByte) {
//        //notice that the top 128 ascii characters have the same representation as their utf-8 counterparts
//        //this allow us to do the following comparison
//
//        if (opCounter==2)
//        {
//            info.putIfAbsent("op",popString());
//        }
//
//        else if (opCounter > 2 && (Integer.parseInt(info.get("op")))<4)
//        {
//            OPMessageClass ans;
//            if (nextByte == '0' && userInfoCounter==0) {
//                info.putIfAbsent("userName",popString());
//                userInfoCounter++;
//            }
//            else if(nextByte == '0'&& userInfoCounter==1){
//                info.putIfAbsent("password",popString());
//                switch (info.get("op")) {
//                    case "1":
//                        ans = new OP1AdminRegMessage(Integer.parseInt(info.get("op")), info.get("userName"), info.get("password"));
//                        break;
//                    case "2":
//                        ans = new OP2StudentRegMessage(Integer.parseInt(info.get("op")), info.get("userName"), info.get("password"));
//                        break;
//                    default:
//                        ans = new OP3LoginRequestMessage(Integer.parseInt(info.get("op")), info.get("userName"), info.get("password"));
//                        break;
//                }
//                userInfoCounter = 0;
//                info.clear();
//                return ans;
//            }
//        }
//
//        else if (opCounter > 2 && (info.get("op").equals("8"))) {
//            OPMessageClass ans;
//            if (nextByte == '0') {
//                info.putIfAbsent("studentUsername", popString());
//                ans = new OP8PrintStudStatMessage(Integer.parseInt(info.get("op")), info.get("studentUsername"));
//                info.clear();
//                return ans;
//            }
//        }
//        else if(opCounter > 2 && (Integer.parseInt(info.get("op"))>4 && Integer.parseInt(info.get("op"))<=10)){
//            OPMessageClass ans;
//            if (len==2) {
//                info.putIfAbsent("courseNumber", popString());
//                switch (info.get("op")) {
//                    case "5":
//                        ans = new OP5RegToCourseMessage(Integer.parseInt(info.get("op")), Integer.parseInt(info.get("courseNumber")));
//                        break;
//                    case "6":
//                        ans = new OP6CheckKdamCourseMessage(Integer.parseInt(info.get("op")), Integer.parseInt(info.get("courseNumber")));
//                        break;
//                    case "7":
//                        ans = new OP7PrintCourseStatMessage(Integer.parseInt(info.get("op")), Integer.parseInt(info.get("courseNumber")));
//                        break;
//                    case "9":
//                        ans = new OP9CheckIfRegMessage(Integer.parseInt(info.get("op")), Integer.parseInt(info.get("courseNumber")));
//                        break;
//                    default:
//                        ans = new OP10UnregCourseMessage(Integer.parseInt(info.get("op")), Integer.parseInt(info.get("courseNumber")));
//                        break;
//                }
//                info.clear();
//                return ans;
//            }
//        }
//        else if (opCounter>2){
//            OPMessageClass ans;
//            if (info.get("op").equals("4")) {
//                ans = new OP4LogoutMessage(Integer.parseInt(info.get("op")));
//            }
//            else  {
//                ans = new OP11CheckMyCurrCoursesMessage(Integer.parseInt(info.get("op")));
//            }
//            info.clear();
//            return ans;
//        }
//
//        pushByte(nextByte);
//        return null; //not a line yet
//
////        if (nextByte == '\n') {
////            return popString();
////        }
//    }
//
//    @Override
//    public byte[] encode(OPMessageClass message) {
//        return (message.toString() + "\n").getBytes(); //uses utf8 by default
//    }
//
//    private void pushByte(byte nextByte) {
//        if (len >= bytes.length) {
//            bytes = Arrays.copyOf(bytes, len * 2);
//        }
//
//        bytes[len++] = nextByte;
//        opCounter++;
//    }
//
//    private String popString() {
//        //notice that we explicitly requesting that the string will be decoded from UTF-8
//        //this is not actually required as it is the default encoding in java.
//        String result = new String(bytes, 0, len, StandardCharsets.UTF_8);
//        len = 0;
//        return result;
//    }
//}
