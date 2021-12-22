package com.yufeng.concurrency.juc.lock.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description
 *      1. 不可重入锁
 *      2. 基于CAS+自旋实现
 * @author yufeng
 * @create 2020-03-18
 */
public class NonReentrantLockByCAS {

    private AtomicReference<Thread> owner = new AtomicReference<>();

    /**
     * 加锁: 锁被占用时阻塞, 直到锁被释放
     * 使用CAS替代synchronized
     */
    public void lock() throws InterruptedException {
        Thread thread = Thread.currentThread();

        /** 如果当前值为null, 则替换为thread, 并返回true, 否则返回false */
        while (!owner.compareAndSet(null, thread)) {
            /** 用于减缓打印速度, 否则输出太多 */
            TimeUnit.SECONDS.sleep(1);
            System.out.println("需等待" + owner.get().getName() + "释放锁");
        }
    }


    public void unlock() {
        Thread thread = Thread.currentThread();
        if (owner.compareAndSet(thread, null)) {
            System.out.println(thread.getName() + " 释放了锁");
            return;
        }
        System.out.println("当前线程没有持有锁, 不具备资格释放!");
        throw new IllegalArgumentException();
    }


    public static void main(String[] args) {
        NonReentrantLockByCAS nonReentrantLock = new NonReentrantLockByCAS();
        try {
            /** 第一次调用获得锁 */
            nonReentrantLock.lock();
            /** 相同对象第二次调用无法获得, 程序会陷入阻塞。 表明该锁不可重入 */
            nonReentrantLock.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
