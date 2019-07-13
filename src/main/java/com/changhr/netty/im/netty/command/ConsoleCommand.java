package com.changhr.netty.im.netty.command;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 控制台要执行的操作接口
 * @author changhr2013
 * @date 2019/7/13
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
