package com.changhr.netty.im.netty.handler.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 检测连接假死的处理逻辑
 *
 * @author changhr
 * @create 2019-10-25 15:51
 */
public class IMIdleStateHandler extends IdleStateHandler {

    private static final int READER_IDLE_TIME = 15;

    public IMIdleStateHandler() {
        /*
         * 参数设置为 0 时，表示我们不关心这类条件
         * 1. 读空闲时间，指的是在这段时间内如果没有数据读到，就表示连接假死
         * 2. 写空闲时间，指的是在这段时间如果没有写数据，就表示连接假死
         * 3. 读写空闲时间，表示如果这段时间内如果没有产生数据读或者写，就表示连接假死
         * 4. 时间单位
         */
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println(READER_IDLE_TIME + "秒内未读到数据，关闭连接");
        ctx.channel().close();
    }
}
