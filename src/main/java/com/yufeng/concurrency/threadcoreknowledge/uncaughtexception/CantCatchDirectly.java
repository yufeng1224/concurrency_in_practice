package com.yufeng.concurrency.threadcoreknowledge.uncaughtexception;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *     1. 子线程出现未捕获的异常
 *     2. 主线程不加try-catch, 创建4个线程依次执行
 *     3. 结果: 主线程依次抛出4个异常, 程序不会捕获异常, 也无法停止
 * @author yufeng
 * @create 2020-02-23
 */
public class CantCatchDirectly implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        new Thread(new CantCatchDirectly(), "MyThread-1").start();
        TimeUnit.SECONDS.sleep(3);

        new Thread(new CantCatchDirectly(), "MyThread-2").start();
        TimeUnit.SECONDS.sleep(3);

        new Thread(new CantCatchDirectly(), "MyThread-3").start();
        TimeUnit.SECONDS.sleep(3);

        new Thread(new CantCatchDirectly(), "MyThread-4").start();
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程正在执行");
        int i = 1/0;
        System.out.println(Thread.currentThread().getName() + "线程是否继续执行");
        System.out.println(i);
    }
}
