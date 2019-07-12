package com.changhr.netty.im.netty;

import com.changhr.netty.im.netty.handler.*;
import com.changhr.netty.im.netty.pack.MessageRequestPacket;
import com.changhr.netty.im.netty.utils.LoginUtil;
import com.changhr.netty.im.netty.utils.PacketCodeC;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
                        pipeline.addLast(new PacketDecoder());
                        pipeline.addLast(new LoginResponseHandler());
                        pipeline.addLast(new MessageResponseHandler());
                        pipeline.addLast(new PacketEncoder());
                    }
                });

        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true);
        connect(bootstrap, "127.0.0.1", 8000, 3);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("connect success! enjoy it!");
                Channel channel = ((ChannelFuture) future).channel();
                // 连接成功之后，启动控制台线程
                startConsoleThread(channel);
            } else {
                System.out.println("connect failed! retrying...");
                if (retry > 0) {
                    connect(bootstrap, host, port, retry - 1);
                }
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            while (!Thread.interrupted()) {
                if (LoginUtil.hasLogin(channel)) {
                    System.out.println("输入消息发送至服务器: ");
                    Scanner scanner = new Scanner(System.in);
                    String line = scanner.nextLine();

                    MessageRequestPacket requestPacket = new MessageRequestPacket(line);
                    ByteBuf buffer = PacketCodeC.getInstance().encode(channel.alloc().buffer(), requestPacket);
                    channel.writeAndFlush(buffer);
                }
            }
        });
    }

}
