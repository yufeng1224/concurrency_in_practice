package com.yufeng.concurrency.threadcoreknowledge.synchronize.base;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. 对象锁用法(一): 代码块形式, 手动指定锁对象
 *      2. 指定Object实例为锁对象
 * @author yufeng
 * @create 2020-02-21
 */
public class SynchronizedObjectLock02 extends BaseMain implements Runnable{

    static SynchronizedObjectLock02 instance = new SynchronizedObjectLock02();

    Object lock1 = new Object();
    Object lock2 = new Object();

    /**
     * 方法中存在多个锁对象
     */
    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println("对象锁的代码块形式, 当前线程名: " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "运行结束");
        }

        synchronized (lock2) {
            System.out.println("对象锁的代码块形式, 当前线程名: " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "运行结束");
        }
    }

    public static void main(String[] args) {
        executeShow(instance);
    }
}
