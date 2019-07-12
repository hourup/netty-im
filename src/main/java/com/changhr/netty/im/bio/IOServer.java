package com.changhr.netty.im.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 闪电侠
 */
public class IOServer {
    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(8000);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            while (true) {
                try {
                    // (1) 阻塞方法获取新的连接
                    Socket socket = serverSocket.accept();
                    if (socket.isConnected()) {

                        executorService.execute(

                                // (2) 每一个新的连接都创建一个线程，负责读取数据
                                () -> {
                                    try {
                                        int len;
                                        byte[] data = new byte[1024];
                                        InputStream inputStream = socket.getInputStream();
                                        // (3) 按字节流方式读取数据
                                        while ((len = inputStream.read(data)) != -1) {
                                            System.out.println(new String(data, 0, len));
                                        }
                                    } catch (IOException e) {
                                    }
                                }
                        );
                    }

                } catch (IOException e) {
                }

            }
        });
    }
}