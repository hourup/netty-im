package com.changhr.netty.im.netty.pack;

/**
 * @author changhr2013
 * @date 2019/7/13
 */
public class LogoutRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
