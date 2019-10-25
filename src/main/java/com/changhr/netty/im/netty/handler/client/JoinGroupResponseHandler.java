package com.changhr.netty.im.netty.handler.client;

import com.changhr.netty.im.netty.pack.server.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author changhr2013
 * @date 2019/7/14
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket joinGroupRspPacket) throws Exception {
        if (joinGroupRspPacket.isSuccess()) {
            System.out.println("加入群[" + joinGroupRspPacket.getGroupId() + "]成功！");
        } else {
            System.out.println("加入群[" + joinGroupRspPacket.getGroupId() + "失败，原因为：" + joinGroupRspPacket.getReason());
        }
    }
}