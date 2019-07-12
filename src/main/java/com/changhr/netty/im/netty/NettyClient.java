package com.changhr.netty.im.netty;

import com.changhr.netty.im.netty.handler.HelloClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author changhr
 * @create 2019-07-11 13:00
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        ChannelPipeline pipeline = nioSocketChannel.pipeline();
                        pipeline.addLast(new HelloClientHandler());
                    }
                });

        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true);

        ChannelFuture future = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8000));
        future.addListener((ChannelFutureListener) channelFuture -> {
            if (channelFuture.isSuccess()){
                System.out.println("connect success!");
            } else {
                System.out.println("connect failed!");
                channelFuture.cause().printStackTrace();
            }
        });

    }

}
