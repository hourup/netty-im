package com.changhr.netty.im.netty.handler;

import com.changhr.netty.im.netty.pack.LoginRequestPacket;
import com.changhr.netty.im.netty.pack.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author changhr
 * @create 2019-07-12 10:33
 */
@SuppressWarnings("Duplicates")
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("a client is connected.");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginRspPacket = new LoginResponsePacket();
        loginRspPacket.setVersion(loginRequestPacket.getVersion());
        // 登录校验
        if (valid(loginRequestPacket)) {
            System.out.println("login valid success.");
            // 校验成功
            loginRspPacket.setSuccess(true);
        } else {
            // 校验失败
            loginRspPacket.setSuccess(false).setReason("账号密码校验失败！");
        }
        ctx.channel().writeAndFlush(loginRspPacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
