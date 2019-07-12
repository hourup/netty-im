package com.changhr.netty.im.netty.handler.server;

import com.changhr.netty.im.netty.session.UserSession;
import com.changhr.netty.im.netty.utils.LoginUtil;
import com.changhr.netty.im.netty.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author changhr
 * @create 2019-07-12 11:45
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!SessionUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (LoginUtil.hasLogin(ctx.channel())) {
            System.out.println("当前连接登录验证完毕，无需再次验证，AuthHandler 被移除！");
        } else {
            System.out.println("无登录验证，强制关闭连接！");
        }
    }
}
