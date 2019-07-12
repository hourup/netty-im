package com.changhr.netty.im.netty.handler.server;

import com.changhr.netty.im.netty.pack.LoginRequestPacket;
import com.changhr.netty.im.netty.pack.LoginResponsePacket;
import com.changhr.netty.im.netty.session.UserSession;
import com.changhr.netty.im.netty.utils.LoginUtil;
import com.changhr.netty.im.netty.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

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
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginReqPacket) throws Exception {
        LoginResponsePacket loginRspPacket = new LoginResponsePacket();
        loginRspPacket.setVersion(loginReqPacket.getVersion());
        loginRspPacket.setUserName(loginReqPacket.getUsername());
        // 登录校验
        if (valid(loginReqPacket)) {
            System.out.println("login valid success.");
            String userId = randomUserId();
            // 校验成功
            loginRspPacket.setSuccess(true)
                    .setUserId(userId);
            System.out.println("[" + loginReqPacket.getUsername() + "]登录成功");
            SessionUtil.bindSession(new UserSession(userId, loginReqPacket.getUsername()), ctx.channel());
        } else {
            System.out.println("login valid failed.");
            // 校验失败
            loginRspPacket.setSuccess(false).setReason("账号密码校验失败！");
        }
        ctx.channel().writeAndFlush(loginRspPacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }
}
