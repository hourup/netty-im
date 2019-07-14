package com.changhr.netty.im.netty.pack;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author changhr2013
 * @date 2019/7/14
 */
@Data
@Accessors(chain = true)
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }
}
