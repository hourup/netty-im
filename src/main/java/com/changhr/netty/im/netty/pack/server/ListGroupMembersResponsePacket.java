package com.changhr.netty.im.netty.pack.server;

import com.changhr.netty.im.netty.pack.Command;
import com.changhr.netty.im.netty.pack.Packet;
import com.changhr.netty.im.netty.session.UserSession;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author changhr2013
 * @date 2019/7/14
 */
@Data
@Accessors(chain = true)
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<UserSession> userSessionList;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
