package com.changhr.netty.im.bio;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 客户端
 *
 * @author changhr
 * @create 2019-07-11 11:17
 */
public class IOClient {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                for (int i = 0; i < 10; i++) {
                    try {
                        System.out.println("send message: " + i);
                        socket.getOutputStream().write((Instant.now().toString() + ": hello world").getBytes(StandardCharsets.UTF_8));
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
