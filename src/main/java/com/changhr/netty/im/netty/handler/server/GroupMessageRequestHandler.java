package com.changhr.netty.im.netty.handler.server;

import com.changhr.netty.im.netty.pack.client.GroupMessageRequestPacket;
import com.changhr.netty.im.netty.pack.server.GroupMessageResponsePacket;
import com.changhr.netty.im.netty.session.UserSession;
import com.changhr.netty.im.netty.utils.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author changhr2013
 * @date 2019/7/14
 */
@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    protected GroupMessageRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket groupMessageReqPacket) throws Exception {
        String toGroupId = groupMessageReqPacket.getToGroupId();
        String message = groupMessageReqPacket.getMessage();

        UserSession userSession = SessionUtil.getSession(ctx.channel());

        GroupMessageResponsePacket groupMessageRspPacket = new GroupMessageResponsePacket()
                .setFromGroupId(toGroupId)
                .setMessage(message)
                .setFromUser(userSession);

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(toGroupId);
        channelGroup.writeAndFlush(groupMessageRspPacket);
    }
}
