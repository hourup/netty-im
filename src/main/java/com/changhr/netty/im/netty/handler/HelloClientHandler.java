package com.changhr.netty.im.netty.handler;

import com.changhr.netty.im.netty.pack.LoginRequestPacket;
import com.changhr.netty.im.netty.pack.LoginResponsePacket;
import com.changhr.netty.im.netty.pack.Packet;
import com.changhr.netty.im.netty.utils.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.UUID;

/**
 * @author changhr
 * @create 2019-07-11 15:36
 */
public class HelloClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(Instant.now().toString() + ": client login...");

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString())
                .setUsername("changhr")
                .setPassword("pwd");

        ByteBuf encode = PacketCodeC.getInstance().encode(ctx.alloc().buffer(), loginRequestPacket);

        ctx.channel().writeAndFlush(encode);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.getInstance().decode(byteBuf);

        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

            if (loginResponsePacket.isSuccess()) {
                System.out.println(Instant.now().toString() + ": 客户端登录成功");
            } else {
                System.out.println(Instant.now().toString() + ": 客户端登录失败，原因: " + loginResponsePacket.getReason());
            }
        }
    }
}
