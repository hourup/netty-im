package com.changhr.netty.im.netty.handler.client;

import com.changhr.netty.im.netty.pack.server.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author changhr2013
 * @date 2019/7/14
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket quitGroupRspPacket) throws Exception {
        if (quitGroupRspPacket.isSuccess()) {
            System.out.println("成功退出群聊[" + quitGroupRspPacket.getGroupId() + "]");
        } else {
            System.out.println("退出群聊[" + quitGroupRspPacket.getGroupId() + "]失败，原因是：" + quitGroupRspPacket.getReason());
        }
    }
}
