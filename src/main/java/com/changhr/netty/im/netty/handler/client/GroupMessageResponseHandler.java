package com.changhr.netty.im.netty.handler.client;

import com.changhr.netty.im.netty.pack.server.GroupMessageResponsePacket;
import com.changhr.netty.im.netty.session.UserSession;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author changhr2013
 * @date 2019/7/14
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket groupMessageRspPacket) throws Exception {
        String fromGroupId = groupMessageRspPacket.getFromGroupId();
        String message = groupMessageRspPacket.getMessage();
        UserSession fromUser = groupMessageRspPacket.getFromUser();

        System.out.println("[收到群消息]: ["+ fromGroupId +"]["+ fromUser +"] -> " + message);
    }
}
