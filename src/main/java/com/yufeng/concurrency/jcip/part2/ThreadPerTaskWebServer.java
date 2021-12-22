package com.yufeng.concurrency.jcip.part2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description
 *      1. 在Web服务器中为每个请求启动一个新的线程(不要这么做)
 * @author yufeng
 * @create
 */
public class ThreadPerTaskWebServer {

    public static void main(String[] args) throws IOException  {
        ServerSocket socket = new ServerSocket(80);

        /**
         * 1. 任务处理过程从主线程中分离出来, 使得主循环能够更快地重新等待下一个到来的连接
         * 2. 任务可以并行处理, 从而能同时服务多个请求
         * 3. 任务处理代码必须是线程安全的, 因为当有多个任务时会并发地调用这段代码
         */
        while (true) {
            final Socket connection = socket.accept();
            Runnable task = () -> handleRequest(connection);
            new Thread(task).start();
        }
    }

    private static void handleRequest(Socket connection) {
        // request-handling logic here
    }
}
