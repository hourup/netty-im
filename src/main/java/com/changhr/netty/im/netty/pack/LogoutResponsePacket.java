package com.changhr.netty.im.netty.pack;

import lombok.Data;

/**
 * @author changhr2013
 * @date 2019/7/13
 */
@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }
}
