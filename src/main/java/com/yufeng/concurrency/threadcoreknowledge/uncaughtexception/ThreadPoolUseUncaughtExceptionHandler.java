package com.yufeng.concurrency.threadcoreknowledge.uncaughtexception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description
 *      1. 线程池处理线程使用 UncaughtExceptionHandler
 *      2. 在方法内部的首行就设置
 * @author yufeng
 * @create 2020-02-23
 */
public class ThreadPoolUseUncaughtExceptionHandler {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new ThreadPoolTask());
        service.shutdown();
    }
}


class ThreadPoolTask implements Runnable {

    @Override
    public void run() {
        /** 方法初始第一行定义 UncaughtExceptionHandler */
        Thread.currentThread().setUncaughtExceptionHandler(new LocalUncaughtExceptionHandler());
    }
}


class LocalUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("==Exception: " + e.getMessage());

        /** 打印异常日志, 关闭资源, 连接等操作... */
    }
}
