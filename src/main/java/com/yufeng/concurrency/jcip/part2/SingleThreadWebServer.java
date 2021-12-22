package com.yufeng.concurrency.jcip.part2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description
 *      1. 串行的Web服务器
 *      2. 代码理论上是正确的, 但在实际生产环境中的执行性能会很糟糕, 因为它每次只能处理一个请求
 *      3. 总结: 在服务器应用程序中, 串行处理机制通常无法提供高吞吐量或快速响应性
 * @author yufeng
 * @create
 */
public class SingleThreadWebServer {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            Socket connection = socket.accept();
            handleRequest(connection);
        }
    }

    private static void handleRequest(Socket connection) {
        // request-handle logic here
    }

}
