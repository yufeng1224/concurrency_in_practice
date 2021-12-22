package com.yufeng.concurrency.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. 演示关闭线程池相关的方法
 *      2. shutdown():  线程池不接受新任务, 但会处理完排队任务
 *      3. isTerminated(): 所有任务都已运行结束或者中止, 线程池已经退出
 * @author yufeng
 * @create 2020-03-10
 */
public class ShutDown01 {

    public static void main(String[] args) throws InterruptedException{
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ShutDownTask());
        }

        TimeUnit.SECONDS.sleep(2);

        System.out.println("调用shutdown前: " + executorService.isShutdown());       // false
        executorService.shutdown();
        System.out.println("调用shutdown后: " + executorService.isShutdown());       // true

        /** 调用shutdown()后无法再提交任务, 抛出RejectedExecutionException */
        try {
            executorService.execute(new ShutDownTask());
        } catch (Exception e) {
            System.out.println("线程池已被关闭, 无法在提交任务!");
        }

        /** 判断线程池是否已经完全关闭 */
        System.out.println("线程池是否完全关闭: " + executorService.isTerminated());    // false
        TimeUnit.SECONDS.sleep(10);
        System.out.println("线程池是否完全关闭: " + executorService.isTerminated());    // true
    }
}


class ShutDownTask implements Runnable {

    @Override
    public void run() {
        try {
            /** 调用showdownNow()方法后, 会调用Thread类的interrupt()方法, sleep()会响应中断, 并抛出异常 */
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断了, 这个是用来测试shutdownNow()的");
        }
    }
}