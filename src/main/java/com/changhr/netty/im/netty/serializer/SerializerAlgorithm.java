package com.changhr.netty.im.netty.serializer;

/**
 * @author changhr
 * @create 2019-07-11 17:34
 */
public interface SerializerAlgorithm {
    /** json 序列化标识 */
    byte JSON = 1;

    byte PROTOBUF = 2;
}
