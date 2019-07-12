package com.changhr.netty.im.netty.pack;

/**
 * @author changhr
 * @create 2019-07-11 17:28
 */
public interface Command {
    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;
}
