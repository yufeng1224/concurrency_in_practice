package com.yufeng.concurrency.threadcoreknowledge.synchronize.base;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description
 *      Lock锁与synchronized等价代码演示
 * @author yufeng
 * @create 2020-02-21
 */
public class SynchronizedToLock {

    Lock lock = new ReentrantLock();


    private synchronized void method1() {
        System.out.println("synchronized形式同步代码块");
    }


    private void method2() {
        lock.lock();
        try {
            System.out.println("Lock形式同步代码块");
        } finally {
            lock.lock();
        }
    }


    public static void main(String[] args) {
        SynchronizedToLock s = new SynchronizedToLock();
        s.method1();
        s.method2();
    }

}
