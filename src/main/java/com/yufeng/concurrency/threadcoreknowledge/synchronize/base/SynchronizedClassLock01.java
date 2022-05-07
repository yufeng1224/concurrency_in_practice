package com.yufeng.concurrency.threadcoreknowledge.synchronize.base;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. 类锁用法(一): 静态方法锁
 *      2. 方法上需加static和synchronized关键字
 *      3. 类锁可以保证不同的实例都能够一个个串行的去执行
 * @author yufeng
 * @create 2020-02-21
 */
public class SynchronizedClassLock01 extends BaseMain implements Runnable{

    static SynchronizedClassLock01 instance1 = new SynchronizedClassLock01();
    static SynchronizedClassLock01 instance2 = new SynchronizedClassLock01();

    @Override
    public void run() {
        method1();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        method2();
    }

    /**
     * 不同的线程实例也需串行执行该方法。
     */
    public static synchronized void method1() {
        System.out.println("类锁的第一种形式形式: 静态方法锁形式。当前线程名: " + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }

    /**
     * 去掉static关键字: 不同的线程实例可以同时执行该段代码。
     */
    public synchronized void method2() {
        System.out.println("这是对象锁的形式。 当前线程名: " + Thread.currentThread().getName());
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

