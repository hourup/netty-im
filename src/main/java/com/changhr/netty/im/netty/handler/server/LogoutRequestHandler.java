package com.changhr.netty.im.netty.handler.server;

import com.changhr.netty.im.netty.pack.client.LogoutRequestPacket;
import com.changhr.netty.im.netty.pack.server.LogoutResponsePacket;
import com.changhr.netty.im.netty.utils.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author changhr2013
 * @date 2019/7/13
 */
@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    /** 单例 */
    public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

    protected LogoutRequestHandler() {};

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket logoutReqPacket) throws Exception {
        LogoutResponsePacket logoutRspPacket = new LogoutResponsePacket();

        SessionUtil.unBindSession(ctx.channel());
        logoutRspPacket.setSuccess(true);

        ctx.pipeline().addAfter(ctx.executor(), "login", "auth", new AuthHandler());

        ctx.channel().writeAndFlush(logoutRspPacket);
    }
}
