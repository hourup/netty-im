package com.changhr.netty.im.netty.pack.server;

import com.changhr.netty.im.netty.pack.Command;
import com.changhr.netty.im.netty.pack.Packet;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author changhr2013
 * @date 2019/7/12
 */
@Data
@Accessors(chain = true)
public class LoginResponsePacket extends Packet {

    private String userId;

    private String userName;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
