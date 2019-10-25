package com.changhr.netty.im.netty.pack.server;

import com.changhr.netty.im.netty.pack.Command;
import com.changhr.netty.im.netty.pack.Packet;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author changhr2013
 * @date 2019/7/14
 */
@Data
@Accessors(chain = true)
public class JoinGroupResponsePacket extends Packet {

    private boolean success;

    private String groupId;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }
}
