package com.changhr.netty.im.netty.pack.server;

import com.changhr.netty.im.netty.pack.Command;
import com.changhr.netty.im.netty.pack.Packet;
import com.changhr.netty.im.netty.session.UserSession;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author changhr2013
 * @date 2019/7/14
 */
@Data
@Accessors(chain = true)
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private UserSession fromUser;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_RESPONSE;
    }
}
