package com.changhr.netty.im.netty;

import com.changhr.netty.im.netty.handler.*;
import com.changhr.netty.im.netty.handler.server.*;
import com.changhr.netty.im.netty.handler.LifeCycleTestHandler;
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
                            pipeline.addLast(new Spliter());
                            pipeline.addLast(new PacketDecoder());
                            pipeline.addLast("login", new LoginRequestHandler());
                            pipeline.addLast(new AuthHandler());
                            pipeline.addLast(new CreateGroupRequestHandler());
                            pipeline.addLast(new JoinGroupRequestHandler());
                            pipeline.addLast(new GroupMessageRequestHandler());
                            pipeline.addLast(new MessageRequestHandler());
                            pipeline.addLast(new ListGroupMembersRequestHandler());
                            pipeline.addLast(new QuitGroupRequestHandler());
                            pipeline.addLast(new LogoutRequestHandler());
                            pipeline.addLast(new PacketEncoder());
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
