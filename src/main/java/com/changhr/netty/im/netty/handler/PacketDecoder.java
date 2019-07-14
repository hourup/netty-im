package com.changhr.netty.im.netty.handler;

import com.changhr.netty.im.netty.pack.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author changhr
 * @create 2019-07-12 10:30
 */
@ChannelHandler.Sharable
public class PacketDecoder extends ByteToMessageDecoder {

    public static final PacketDecoder INSTANCE = new PacketDecoder();

    protected PacketDecoder(){
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(PacketCodeC.getInstance().decode(byteBuf));
    }
}
