package com.changhr.netty.im.netty.handler.client;

import com.changhr.netty.im.netty.pack.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author changhr2013
 * @date 2019/7/14
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket createGroupRspPacket) throws Exception {
        System.out.print("群创建成功，id 为[" + createGroupRspPacket.getGroupId() + "]，");
        System.out.println("群里面有: " + createGroupRspPacket.getUserNameList());
    }
}
