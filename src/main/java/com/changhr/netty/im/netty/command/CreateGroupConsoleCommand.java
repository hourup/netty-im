package com.changhr.netty.im.netty.command;

import com.changhr.netty.im.netty.pack.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author changhr2013
 * @date 2019/7/13
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {

    private static final String USER_ID_SPLITTER = ",";
        @Override
        public void exec(Scanner scanner, Channel channel) {
            CreateGroupRequestPacket createGroupReqPacket = new CreateGroupRequestPacket();
            System.out.println("[拉人群聊]输入 userId 列表，userId 之间英文逗号隔开：");
            String userIds = scanner.next();
            createGroupReqPacket.setUserIdList(Arrays.asList(userIds.split(USER_ID_SPLITTER)));
            channel.writeAndFlush(createGroupReqPacket);
    }
}
