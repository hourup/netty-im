package com.changhr.netty.im.netty.handler.server;

import com.changhr.netty.im.netty.pack.client.QuitGroupRequestPacket;
import com.changhr.netty.im.netty.pack.server.QuitGroupResponsePacket;
import com.changhr.netty.im.netty.utils.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author changhr2013
 * @date 2019/7/14
 */
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    protected QuitGroupRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket quitGroupReqPacket) throws Exception {
        // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 移除
        String groupId = quitGroupReqPacket.getGroupId();

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        boolean remove = channelGroup.remove(ctx.channel());
        QuitGroupResponsePacket quitGroupRspPacket = new QuitGroupResponsePacket();

        if (remove) {
            // 2. 构造退群响应发送给客户端
            quitGroupRspPacket.setGroupId(groupId).setSuccess(true);
        } else {
            quitGroupRspPacket.setGroupId(groupId).setSuccess(false).setReason("退出群聊[" + groupId + "]失败");
        }

        ctx.channel().writeAndFlush(quitGroupRspPacket);
    }
}
