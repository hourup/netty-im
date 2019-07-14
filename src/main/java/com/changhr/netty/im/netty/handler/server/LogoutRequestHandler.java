package com.changhr.netty.im.netty.handler.server;

import com.changhr.netty.im.netty.pack.LogoutRequestPacket;
import com.changhr.netty.im.netty.pack.LogoutResponsePacket;
import com.changhr.netty.im.netty.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author changhr2013
 * @date 2019/7/13
 */
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket logoutReqPacket) throws Exception {
        LogoutResponsePacket logoutRspPacket = new LogoutResponsePacket();

        SessionUtil.unBindSession(ctx.channel());
        logoutRspPacket.setSuccess(true);

        ctx.pipeline().addAfter(ctx.executor(), "login", "auth", new AuthHandler());

        ctx.channel().writeAndFlush(logoutRspPacket);
    }
}
