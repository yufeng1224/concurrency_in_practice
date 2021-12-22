package com.yufeng.concurrency.threadcoreknowledge.synchronize.base;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      类锁用法(二):
 *         1. *.class
 * @author yufeng
 * @create 2020-02-21
 */
public class SynchronizedClassLock02 extends BaseMain implements Runnable{

    static SynchronizedClassLock02 instance1 = new SynchronizedClassLock02();

    static SynchronizedClassLock02 instance2 = new SynchronizedClassLock02();


    @Override
    public void run() {
        method();
    }


    public void method() {
        synchronized (SynchronizedClassLock02.class) {
            System.out.println("类锁用法(二)：synchronized(*.class)。当前线程" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行结束");
        }
    }


    public static void main(String[] args) {
        executeShow(instance1, instance2);
    }
}
