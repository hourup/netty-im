package com.changhr.netty.im.netty.handler.server;

import com.changhr.netty.im.netty.pack.JoinGroupRequestPacket;
import com.changhr.netty.im.netty.pack.JoinGroupResponsePacket;
import com.changhr.netty.im.netty.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author changhr2013
 * @date 2019/7/14
 */
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket joinGroupReqPacket) throws Exception {
        String groupId = joinGroupReqPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.add(ctx.channel());

        JoinGroupResponsePacket joinGroupRspPacket = new JoinGroupResponsePacket()
                .setGroupId(groupId)
                .setSuccess(true);

        ctx.writeAndFlush(joinGroupRspPacket);
    }
}
