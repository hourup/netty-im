package com.changhr.netty.im.netty;

import com.changhr.netty.im.netty.command.ConsoleCommandManager;
import com.changhr.netty.im.netty.command.LoginConsoleCommand;
import com.changhr.netty.im.netty.handler.*;
import com.changhr.netty.im.netty.handler.client.*;
import com.changhr.netty.im.netty.utils.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author changhr
 * @create 2019-07-11 13:00
 */
@Slf4j
public class NettyClient {

    private static final int MAX_RETRY = 3;

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        ChannelPipeline pipeline = nioSocketChannel.pipeline();
                        pipeline.addLast(new Spliter());
                        pipeline.addLast(new PacketDecoder());
                        pipeline.addLast(new LoginResponseHandler());
                        pipeline.addLast(new CreateGroupResponseHandler());
                        pipeline.addLast(new MessageResponseHandler());
                        pipeline.addLast(new GroupMessageResponseHandler());
                        pipeline.addLast(new JoinGroupResponseHandler());
                        pipeline.addLast(new QuitGroupResponseHandler());
                        pipeline.addLast(new ListGroupMembersResponseHandler());
                        pipeline.addLast(new LogoutResponseHandler());
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
            } else if (retry == 0) {
                System.err.println("retry count is 0, give up connect.");
            } else {
                System.err.println("connect failed! retrying...");
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连间隔
                int delay = 1 << order;
                bootstrap.config().group().schedule(() ->
                        connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        ExecutorService executorService = Executors.newCachedThreadPool();
        Scanner scanner = new Scanner(System.in);
        executorService.execute(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    loginConsoleCommand.exec(scanner, channel);
                } else {
                    consoleCommandManager.exec(scanner, channel);
                }
            }
        });
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored) {
            log.error("连接被忽略", ignored);
        }
    }

}
