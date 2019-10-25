package com.changhr.netty.im.netty.handler.client;

import com.changhr.netty.im.netty.pack.server.ListGroupMembersResponsePacket;
import com.changhr.netty.im.netty.session.UserSession;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * @author changhr2013
 * @date 2019/7/14
 */
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket listGroupMembersRspPacket) throws Exception {
        String groupId = listGroupMembersRspPacket.getGroupId();
        List<UserSession> userSessionList = listGroupMembersRspPacket.getUserSessionList();

        System.out.println("群聊[" + groupId + "]中的用户列表为：" + userSessionList);
    }
}
