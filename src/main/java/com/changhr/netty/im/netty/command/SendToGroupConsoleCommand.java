package com.changhr.netty.im.netty.command;

import com.changhr.netty.im.netty.pack.client.GroupMessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author changhr2013
 * @date 2019/7/14
 */
public class SendToGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入要发送的群聊id和要发送的消息，中间用空格隔开：");
        String toGroupId = scanner.next();
        String message = scanner.nextLine();
        GroupMessageRequestPacket groupMessageReqPacket = new GroupMessageRequestPacket()
                .setToGroupId(toGroupId)
                .setMessage(message);
        channel.writeAndFlush(groupMessageReqPacket);
    }
}
