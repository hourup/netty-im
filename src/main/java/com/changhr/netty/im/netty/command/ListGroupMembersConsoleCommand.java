package com.changhr.netty.im.netty.command;

import com.changhr.netty.im.netty.pack.client.ListGroupMembersRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author changhr2013
 * @date 2019/7/14
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        ListGroupMembersRequestPacket listGroupMembersReqPacket = new ListGroupMembersRequestPacket();
        System.out.println("请输入要获取群列表的群聊 ID: ");
        String groupId = scanner.next();

        listGroupMembersReqPacket.setGroupId(groupId);
        channel.writeAndFlush(listGroupMembersReqPacket);
    }
}
