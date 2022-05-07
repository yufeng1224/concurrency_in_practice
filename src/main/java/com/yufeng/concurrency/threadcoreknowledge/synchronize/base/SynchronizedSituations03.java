package com.yufeng.concurrency.threadcoreknowledge.synchronize.base;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. 两个线程访问synchronized修饰的静态方法
 *      2. 结论: 线程会串行执行该段代码, 能够保证并发安全; 本质: 持有的是同一个类锁
 * @author yufeng
 * @create 2020-02-21
 */
public class SynchronizedSituations03 extends BaseMain implements Runnable {

    static SynchronizedSituations03 instance1 = new SynchronizedSituations03();
    static SynchronizedSituations03 instance2 = new SynchronizedSituations03();

    @Override
    public void run() {
        method();
    }

    public static synchronized void method() {
        System.out.println("两个线程访问的是synchronized修饰的静态方法情况演示, 当前线程: " + Thread.currentThread().getName());

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "运行结束");
    }

    public static void main(String[] args) {
        executeShow(instance1, instance2);
    }
}
