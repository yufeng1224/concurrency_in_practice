package com.yufeng.concurrency.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. 演示关闭线程池相关的方法
 *      2. awaitTermination(): 检测线程池在指定的时间后, 是否已经完全关闭。任务执行完毕返回true, 否则返回false
 *                             调用此方法后, 主线程会进入阻塞状态
 * @author yufeng
 * @create 2020-03-20
 */
public class ShutDown02 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ShutDownTask());
        }

        TimeUnit.SECONDS.sleep(2);
        executorService.shutdown();

        /** 调用此方法后, 主线程会陷入阻塞状态*/
        boolean b = executorService.awaitTermination(3L, TimeUnit.SECONDS);
        System.out.println("线程池是否已关闭: " + b);

        b = executorService.awaitTermination(4L, TimeUnit.SECONDS);
        System.out.println("线程池是否已关闭: " + b);
    }
}
