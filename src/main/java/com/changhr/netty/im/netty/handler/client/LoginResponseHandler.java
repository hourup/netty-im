package com.changhr.netty.im.netty.handler.client;

import com.changhr.netty.im.netty.pack.LoginResponsePacket;
import com.changhr.netty.im.netty.session.UserSession;
import com.changhr.netty.im.netty.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.Instant;

/**
 * @author changhr
 * @create 2019-07-12 10:44
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(Instant.now().toString() + ": client connect...");

//        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
//        loginRequestPacket.setUsername("changhr")
//                .setPassword("pwd");
//
//        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginRspPacket) throws Exception {
        String userId = loginRspPacket.getUserId();
        String userName = loginRspPacket.getUserName();
        if (loginRspPacket.isSuccess()) {
            System.out.println(Instant.now().toString() + ": 客户端登录成功,userId=[" + userId + "]");
            SessionUtil.bindSession(new UserSession(userId, userName), ctx.channel());
        } else {
            System.out.println(Instant.now().toString() + ": 客户端登录失败，原因: " + loginRspPacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭！");
    }
}
