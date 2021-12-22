package com.yufeng.concurrency.juc.lock.reentrantlock;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. 线程执行synchronized同步代码块时, 再次重入该锁过程中抛出了异常,
 *         则锁的计数器会直接变为0, 并释放锁
 * @author yufeng
 * @create 2020-03-17
 */
public class SynchronizedReentrantException implements Runnable {

    private volatile boolean flag = true;

    @Override
    public void run() {
        synchronized (this) {
            if (flag) {
                methodB();
            } else {
                methodA();
            }
        }
    }


    public synchronized void methodA() {
        System.out.println(Thread.currentThread().getName() + " invoke methodA()");
    }


    public synchronized void methodB() {
        flag = false;
        System.out.println(Thread.currentThread().getName() + " invoke methodB()");

        // 锁的计数器变为2, 此时抛出了异常
        int i = 1/0;

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SynchronizedReentrantException runnable = new SynchronizedReentrantException();

        Thread thread1 = new Thread(runnable, "thread-1");
        Thread thread2 = new Thread(runnable, "thread-2");

        thread1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}
