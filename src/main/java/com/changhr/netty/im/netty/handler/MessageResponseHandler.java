package com.changhr.netty.im.netty.handler;

import com.changhr.netty.im.netty.pack.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.Instant;

/**
 * @author changhr
 * @create 2019-07-12 10:47
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageRspPacket) throws Exception {
        String message = messageRspPacket.getMessage();
        System.out.println(Instant.now().toString() + ": 收到服务端消息: " + message);
    }
}
