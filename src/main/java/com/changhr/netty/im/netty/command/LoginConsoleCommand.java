package com.changhr.netty.im.netty.command;

import com.changhr.netty.im.netty.pack.LoginRequestPacket;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author changhr2013
 * @date 2019/7/13
 */
@Slf4j
public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginReqPacket = new LoginRequestPacket();

        System.out.println("输入用户名登录: ");
        loginReqPacket.setUsername(scanner.nextLine())
                .setPassword("pwd");

        // 发送登录数据包
        channel.writeAndFlush(loginReqPacket);
        waitForLoginResponse();
    }

    private void waitForLoginResponse() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            log.error("登录出错: ", e);
        }
    }
}
