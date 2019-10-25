package com.changhr.netty.im.netty.handler.server;

import com.changhr.netty.im.netty.pack.client.ListGroupMembersRequestPacket;
import com.changhr.netty.im.netty.pack.server.ListGroupMembersResponsePacket;
import com.changhr.netty.im.netty.session.UserSession;
import com.changhr.netty.im.netty.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author changhr2013
 * @date 2019/7/14
 */
@ChannelHandler.Sharable
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {

    public static final ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();

    protected ListGroupMembersRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket listGroupMembersReqPacket) throws Exception {
        String groupId = listGroupMembersReqPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        List<UserSession> userSessionList = new ArrayList<>();
        for (Channel channel : channelGroup) {
            UserSession userSession = SessionUtil.getSession(channel);
            if (userSession != null) {
                userSessionList.add(userSession);
            }
        }

        ListGroupMembersResponsePacket listGroupMembersRspPacket = new ListGroupMembersResponsePacket()
                .setGroupId(groupId)
                .setUserSessionList(userSessionList);
        ctx.channel().writeAndFlush(listGroupMembersRspPacket);
    }
}
