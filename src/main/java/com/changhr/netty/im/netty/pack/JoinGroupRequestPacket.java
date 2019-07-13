package com.changhr.netty.im.netty.pack;

import lombok.Data;

/**
 * @author changhr2013
 * @date 2019/7/14
 */
@Data
public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }
}
