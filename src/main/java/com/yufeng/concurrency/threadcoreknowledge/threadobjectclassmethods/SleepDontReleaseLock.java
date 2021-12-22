package com.yufeng.concurrency.threadcoreknowledge.threadobjectclassmethods;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description
 *      演示sleep不会释放lock, lock需手动释放
 * @author yufeng
 * @create 2020-02-20
 */
public class SleepDontReleaseLock implements Runnable {

    private static final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        System.out.println("线程" + Thread.currentThread().getName() + "获取到了锁");

        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("线程" + Thread.currentThread().getName() + "已经苏醒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程" + Thread.currentThread().getName() + "休眠5秒后继续执行");
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        SleepDontReleaseLock sleepDontReleaseLock = new SleepDontReleaseLock();
        new Thread(sleepDontReleaseLock, "thread-0").start();
        new Thread(sleepDontReleaseLock, "thread-1").start();
    }
}
