package com.yufeng.concurrency.threadcoreknowledge.synchronize.base;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. 两个线程同时访问一个对象的同步方法情况演示
 *      2. 结论: 线程会串行执行该段代码, 能够保证并发安全; 本质: 持有的是同一个对象锁
 * @author yufeng
 * @create 2020-02-21
 */
public class SynchronizedSituations01 extends BaseMain implements Runnable {

    static SynchronizedSituations01 instance = new SynchronizedSituations01();

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("两个线程同时访问同一个对象的同步方法演示, 当前线程: " + Thread.currentThread().getName());

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "运行结束");
        }
    }

    public static void main(String[] args) {
        executeShow(instance);                  // 串行执行
    }

}
