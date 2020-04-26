package com.changhr.netty.im.netty.serializer;

/**
 * @author changhr
 * @create 2020-04-26 16:07
 */
public class ProtoBufSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.PROTOBUF;
    }

    @Override
    public byte[] serialize(Object object) {
        return ProtoStuffUtil.serializeToByte(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return ProtoStuffUtil.deserializeFromByte(bytes, clazz);
    }
}
