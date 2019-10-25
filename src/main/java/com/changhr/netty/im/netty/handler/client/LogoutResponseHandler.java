package com.changhr.netty.im.netty.handler.client;

import com.changhr.netty.im.netty.pack.server.LogoutResponsePacket;
import com.changhr.netty.im.netty.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author changhr2013
 * @date 2019/7/13
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket logoutRspPacket) throws Exception {
        if (logoutRspPacket.isSuccess()) {
            String userName = SessionUtil.getSession(ctx.channel()).getUserName();
            SessionUtil.unBindSession(ctx.channel());
            System.out.println("客户端" + userName + "已登出！");
        } else {
            System.out.println("客户端注销失败，请重试：" + logoutRspPacket.getReason());
        }
    }
}
