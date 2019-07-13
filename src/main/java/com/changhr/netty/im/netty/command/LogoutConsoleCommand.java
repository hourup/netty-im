package com.changhr.netty.im.netty.command;

import com.changhr.netty.im.netty.pack.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author changhr2013
 * @date 2019/7/13
 */
public class LogoutConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutReqPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutReqPacket);
    }
}
