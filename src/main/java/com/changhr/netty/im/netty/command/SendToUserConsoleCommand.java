package com.changhr.netty.im.netty.command;

import com.changhr.netty.im.netty.pack.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author changhr2013
 * @date 2019/7/13
 */
public class SendToUserConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("发送消息给某个用户");
        String toUserId = scanner.next();
        String message = scanner.nextLine();
        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
    }
}
