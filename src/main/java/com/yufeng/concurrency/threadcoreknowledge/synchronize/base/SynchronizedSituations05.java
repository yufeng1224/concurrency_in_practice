package com.yufeng.concurrency.threadcoreknowledge.synchronize.base;

/**
 * @description
 *     访问同一个对象的不同的普通同步方法情况演示
 *        1. 结论: 不同线程访问不同的同步方法将串行运行
 *        2. 本质: 持有的是同一个对象锁
 * @author yufeng
 * @create 2020-02-21
 */
public class SynchronizedSituations05 extends BaseMain implements Runnable {

    static SynchronizedSituations05 instance = new SynchronizedSituations05();

    @Override
    public void run() {
        if ("t1".equals(Thread.currentThread().getName())) {
            method1();
        } else {
            method2();
        }
    }


    public synchronized void method1() {
        System.out.println("普通加锁方法method1。当前线程名" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }


    public synchronized void method2() {
        System.out.println("普通加锁方法method2。当前线程名" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }

    public static void main(String[] args) {
        executeShow(instance, instance);
    }
}
