package com.yufeng.concurrency.threadcoreknowledge.synchronize.base;

/**
 * @description
 *      同时访问同步方法和非同步方法情况演示
 *         1. 结论: 不同的线程会同时执行对应的方法, 相互之间是没有影响的
 *         2. 本质: 一个线程持有锁, 一个线程不持有锁, 线程之间是没有关联的
 * @author yufeng
 * @create 2020-02-21
 */
public class SynchronizedSituations04 extends BaseMain implements Runnable {

    static SynchronizedSituations04 instance = new SynchronizedSituations04();


    @Override
    public void run() {
        if ("t1".equals(Thread.currentThread().getName())) {
            method1();
        } else {
            method2();
        }
    }


    public synchronized void method1() {
        System.out.println("加锁的方法。当前线程名" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }



    public void method2() {
        System.out.println("普通方法。当前线程名" + Thread.currentThread().getName());
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
