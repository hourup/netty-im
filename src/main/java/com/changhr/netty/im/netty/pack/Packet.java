package com.changhr.netty.im.netty.pack;

import lombok.Data;

/**
 * @author changhr
 * @create 2019-07-11 17:27
 */
@Data
public abstract class Packet {

    private Byte version = 1;

    public abstract Byte getCommand();
}
