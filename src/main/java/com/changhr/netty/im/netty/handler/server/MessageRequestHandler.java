package com.changhr.netty.im.netty.handler.server;

import com.changhr.netty.im.netty.pack.MessageRequestPacket;
import com.changhr.netty.im.netty.pack.MessageResponsePacket;
import com.changhr.netty.im.netty.session.UserSession;
import com.changhr.netty.im.netty.utils.SessionUtil;
import io.netty.channel.Channel;
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
        // 1. 拿到消息发送方的会话信息
        UserSession userSession = SessionUtil.getSession(ctx.channel());

        // 2. 通过消息发送方的会话信息构造要发送的消息
        MessageResponsePacket messageRspPacket = new MessageResponsePacket()
                .setFromUserId(userSession.getUserId())
                .setFromUserName(userSession.getUserName())
                .setMessage(messageReqPacket.getMessage());

        // 3. 拿到消息接收方的 channel
        Channel toUserChannel = SessionUtil.getChannel(messageReqPacket.getToUserId());

        // 4. 将消息发送给消息接收方
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(messageRspPacket);
        } else {
            System.err.println("[" + messageReqPacket.getToUserId() + "] 不在线，发送失败！");
        }
    }
}
