package com.changhr.netty.im.netty.serializer;

/**
 * @author changhr
 * @create 2019-07-11 17:32
 */
public interface Serializer {

    Serializer DEFAULT = new ProtoBufSerializer();

    /** 序列化算法 */
    byte getSerializerAlgorithm();

    /** java 对象转换成字节数组 */
    byte[] serialize(Object object);

    /** 字节数组转换为 java 对象 */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
