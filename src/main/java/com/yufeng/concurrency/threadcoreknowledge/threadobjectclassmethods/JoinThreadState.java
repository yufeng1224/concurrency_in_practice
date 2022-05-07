package com.yufeng.concurrency.threadcoreknowledge.threadobjectclassmethods;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. 先join再看mainThread.getState(), 通过debugger看线程join前后状态的对比
 *      2. 代码方式看线程状态
 *      3. debug 方式看线程状态
 * @author yufeng
 * @create 2020-02-20
 */
public class JoinThreadState {

    public static void main(String[] args) throws InterruptedException {

        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(mainThread.getState());      // WAITING
                System.out.println("Thread-0运行结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println("等待子线程运行完毕");

        thread.join();
        System.out.println("子线程运行完毕");

    }
}
