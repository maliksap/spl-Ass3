package bgu.spl.net;

import java.nio.ByteBuffer;

public abstract class Packet {
    abstract ByteBuffer getPacketBytes();
}
