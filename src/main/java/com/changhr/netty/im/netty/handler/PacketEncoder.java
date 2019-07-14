package com.changhr.netty.im.netty.handler;

import com.changhr.netty.im.netty.pack.Packet;
import com.changhr.netty.im.netty.pack.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author changhr
 * @create 2019-07-12 10:31
 */
@ChannelHandler.Sharable
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    public static final PacketEncoder INSTANCE = new PacketEncoder();

    protected PacketEncoder(){
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        PacketCodeC.getInstance().encode(byteBuf, packet);
    }
}
