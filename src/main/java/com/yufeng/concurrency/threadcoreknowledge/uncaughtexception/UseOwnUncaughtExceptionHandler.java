package com.yufeng.concurrency.threadcoreknowledge.uncaughtexception;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      使用自定义的UncaughtExceptionHandler
 * @author yufeng
 * @create 2020-02-23
 */
public class UseOwnUncaughtExceptionHandler implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("捕获器1"));

        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-1").start();
        TimeUnit.SECONDS.sleep(3);

        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-2").start();
        TimeUnit.SECONDS.sleep(3);

        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-3").start();
        TimeUnit.SECONDS.sleep(3);

        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-4").start();
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程正在执行");
        int i = 1/0;
        System.out.println(Thread.currentThread().getName() + "线程是否继续执行");
        System.out.println(i);
        System.out.println(Thread.currentThread().getName() + "线程执行结束");
    }
}



