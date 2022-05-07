package com.yufeng.concurrency.threadcoreknowledge.synchronize.base;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. 同时访问静态同步方法和非静态同步方法情况演示
 *      2. 结论: 不同的线程会同时执行对应的方法, 相互之间是没有影响的
 *      3. 本质: 线程持有的锁不同, 一个线程持有的是类锁, 一个线程持有的是对象锁
 * @author yufeng
 * @create 2020-02-21
 */
public class SynchronizedSituations06 extends BaseMain implements Runnable{

    static SynchronizedSituations06 instance1 = new SynchronizedSituations06();
    static SynchronizedSituations06 instance2 = new SynchronizedSituations06();

    @Override
    public void run() {
        if ("t1".equals(Thread.currentThread().getName())) {
            method1();
        } else {
            method2();
        }
    }

    public synchronized void method1() {
        System.out.println("普通同步方法。当前线程为" + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }

    public  static synchronized void method2() {
        System.out.println("静态同步方法。当前线程为" + Thread.currentThread().getName());
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
