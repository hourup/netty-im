package com.changhr.netty.im.netty.handler.server;

import com.changhr.netty.im.netty.pack.CreateGroupRequestPacket;
import com.changhr.netty.im.netty.pack.CreateGroupResponsePacket;
import com.changhr.netty.im.netty.utils.IDUtil;
import com.changhr.netty.im.netty.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author changhr2013
 * @date 2019/7/13
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupReqPacket) throws Exception {
        List<String> userIdList = createGroupReqPacket.getUserIdList();

        ArrayList<String> userNameList = new ArrayList<>();

        // 1. 创建一个 channel 分组
        DefaultChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        // 2. 筛选出待加入群聊的用户的 channel 和 userName
        userIdList.forEach(userId ->{
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        });

        // 3. 创建群聊创建结果的响应
        String groupId = IDUtil.randomId();
        CreateGroupResponsePacket createGroupRspPacket = new CreateGroupResponsePacket()
                .setSuccess(true)
                .setGroupId(groupId)
                .setUserNameList(userNameList);

        SessionUtil.bindChannelGroup(groupId, channelGroup);
        // 4. 给每个客户端发送拉群消息
        channelGroup.writeAndFlush(createGroupRspPacket);
        System.out.println("群创建成功，id 为[" + groupId + "]");
        System.out.println("群里面有: " + userIdList);

    }
}
