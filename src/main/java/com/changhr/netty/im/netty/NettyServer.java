package com.changhr.netty.im.netty;

import com.changhr.netty.im.netty.handler.*;
import com.changhr.netty.im.netty.handler.server.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author changhr
 * @create 2019-07-11 11:51
 */
public class NettyServer {

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup();

        try {
            serverBootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {

                        @Override
                        protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                            ChannelPipeline pipeline = nioSocketChannel.pipeline();
//                            pipeline.addLast(new LifeCycleTestHandler());
                            pipeline.addLast(new Splitter());
                            pipeline.addLast(PacketCodecHandler.INSTANCE);
                            pipeline.addLast("login", LoginRequestHandler.INSTANCE);
                            pipeline.addLast(AuthHandler.INSTANCE);
                            pipeline.addLast(new CreateGroupRequestHandler());
                            pipeline.addLast(new JoinGroupRequestHandler());
                            pipeline.addLast(new GroupMessageRequestHandler());
                            pipeline.addLast(new MessageRequestHandler());
                            pipeline.addLast(new ListGroupMembersRequestHandler());
                            pipeline.addLast(new QuitGroupRequestHandler());
                            pipeline.addLast(new LogoutRequestHandler());
                        }
                    });
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            bind(serverBootstrap, 8000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void bind(ServerBootstrap serverBootstrap, int port) {
        ChannelFuture channelFuture = serverBootstrap.bind(port);
        channelFuture.addListener((ChannelFutureListener) channelFuture1 -> {

            if (channelFuture1.isSuccess()) {
                System.out.println("bind success at port: " + port);
            } else {
                bind(serverBootstrap, port + 1);
            }
        });
    }

}
