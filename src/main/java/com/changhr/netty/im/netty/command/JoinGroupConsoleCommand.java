package com.changhr.netty.im.netty.command;

import com.changhr.netty.im.netty.pack.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author changhr2013
 * @date 2019/7/14
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        JoinGroupRequestPacket joinGroupReqPacket = new JoinGroupRequestPacket();

        System.out.println("输入 groupId，加入群聊：");
        String groupId = scanner.nextLine();

        joinGroupReqPacket.setGroupId(groupId);
        channel.writeAndFlush(joinGroupReqPacket);
    }
}
