package com.changhr.netty.im.netty.pack;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author changhr2013
 * @date 2019/7/14
 */
@Data
@Accessors(chain = true)
public class GroupMessageRequestPacket extends Packet {

    private String toGroupId;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_REQUEST;
    }
}
