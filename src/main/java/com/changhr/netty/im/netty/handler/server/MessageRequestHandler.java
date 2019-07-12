package com.changhr.netty.im.netty.handler.server;

import com.changhr.netty.im.netty.pack.MessageRequestPacket;
import com.changhr.netty.im.netty.pack.MessageResponsePacket;
import com.changhr.netty.im.netty.utils.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.Instant;

/**
 * @author changhr
 * @create 2019-07-12 10:39
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageReqPacket) throws Exception {
        System.out.println(Instant.now().toString() + ": 收到客户端消息: " + messageReqPacket.getMessage());
        MessageResponsePacket messageRspPacket = new MessageResponsePacket("服务端回复【" + messageReqPacket.getMessage() + "】");
        ctx.channel().writeAndFlush(messageRspPacket);
    }
}
