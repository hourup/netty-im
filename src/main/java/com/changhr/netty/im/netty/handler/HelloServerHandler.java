package com.changhr.netty.im.netty.handler;

import com.changhr.netty.im.netty.pack.LoginRequestPacket;
import com.changhr.netty.im.netty.pack.LoginResponsePacket;
import com.changhr.netty.im.netty.pack.Packet;
import com.changhr.netty.im.netty.utils.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author changhr
 * @create 2019-07-11 15:44
 */
public class HelloServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("a client is connected.");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("reading a client message.");
        ByteBuf requestByteBuf = (ByteBuf) msg;

        // 解码
        Packet packet = PacketCodeC.getInstance().decode(requestByteBuf);

        // 判断是否是登录请求数据包
        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            // 登录校验
            if (valid(loginRequestPacket)) {
                System.out.println("login valid success.");
                // 校验成功
                loginResponsePacket.setSuccess(true);
            } else {
                // 校验失败
                loginResponsePacket.setSuccess(false).setReason("账号密码校验失败！");
            }

            // 编码
            ByteBuf responseByteBuf = PacketCodeC.getInstance().encode(ctx.alloc().buffer(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
