package com.changhr.netty.im.netty.pack;

import lombok.Data;

import java.util.List;

/**
 * @author changhr2013
 * @date 2019/7/13
 */
@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
