package com.changhr.netty.im.netty.command;

import com.changhr.netty.im.netty.pack.client.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author changhr2013
 * @date 2019/7/14
 */
public class QuitGroupConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入要退出的群聊 ID: ");
        String groupId = scanner.next();

        QuitGroupRequestPacket quitGroupReqPacket = new QuitGroupRequestPacket().setGroupId(groupId);

        channel.writeAndFlush(quitGroupReqPacket);
    }
}
