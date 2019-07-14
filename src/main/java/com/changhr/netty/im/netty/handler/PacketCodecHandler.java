package com.changhr.netty.im.netty.handler;

import com.changhr.netty.im.netty.pack.Packet;
import com.changhr.netty.im.netty.pack.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * @author changhr2013
 * @date 2019/7/14
 */
@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, Packet> {

    public static final PacketCodecHandler INSTANCE = new PacketCodecHandler();

    protected PacketCodecHandler(){
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        ByteBuf buffer = ctx.channel().alloc().buffer();
        PacketCodeC.getInstance().encode(buffer, msg);
        out.add(buffer);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        out.add(PacketCodeC.getInstance().decode(msg));
    }
}
