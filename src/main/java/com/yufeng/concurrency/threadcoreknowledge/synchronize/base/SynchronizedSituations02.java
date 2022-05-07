package com.yufeng.concurrency.threadcoreknowledge.synchronize.base;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. 两个线程访问的是两个对象的同步方法
 *      2. 结论: 两个线程会同时执行该段代码, 相互之间是没有影响的; 本质: 持有的是不同的对象锁
 * @author yufeng
 * @create 2020-02-21
 */
public class SynchronizedSituations02 extends BaseMain implements Runnable {

    static SynchronizedSituations02 instance1 = new SynchronizedSituations02();
    static SynchronizedSituations02 instance2 = new SynchronizedSituations02();

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("两个线程访问的是两个对象的同步方法演示, 当前线程: " + Thread.currentThread().getName());

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "运行结束");
        }
    }

    public static void main(String[] args) {
        executeShow(instance1, instance2);              // 并行执行
    }
}
