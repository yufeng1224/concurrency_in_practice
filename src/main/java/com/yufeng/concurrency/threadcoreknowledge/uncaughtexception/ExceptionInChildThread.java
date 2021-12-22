package com.yufeng.concurrency.threadcoreknowledge.uncaughtexception;

/**
 * @description
 *      1. 子线程能够打印异常信息, 但是很难被发现
 *      2. 主线程继续执行自己的业务, 未对子线程的异常做出任何处理
 * @author yufeng
 * @create 2020-02-23
 */
public class ExceptionInChildThread implements Runnable {

    public static void main(String[] args) {
        new Thread(new ExceptionInChildThread()).start();

        for (int i = 0; i < 1000; i ++) {
            System.out.println(i);
        }
    }

    @Override
    public void run() {
        throw new RuntimeException("子线程异常");
    }
}
