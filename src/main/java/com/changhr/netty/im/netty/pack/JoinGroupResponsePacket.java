package com.changhr.netty.im.netty.pack;

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