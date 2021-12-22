package com.yufeng.concurrency.juc.lock.reentrantlock;

/**
 * @description
 *      1. 演示不可重入锁
 *      2. 基于wait/notify实现
 * @author yufeng
 * @create 2020-03-18
 */
public class NonReentrantLockByWait {

    /** 标记持有锁的线程, 空表示无人占有 */
    private Thread owner;

    public synchronized void lock() throws InterruptedException {
        Thread thread = Thread.currentThread();

        while (owner != null) {
            System.out.println("需等待" + owner.getName() + "释放锁");
            wait();
        }

        System.out.println(thread.getName() + " 获得了锁");
        /** 持有锁线程赋值绑定 */
        owner = thread;
    }


    public synchronized void unlock() {
        if (Thread.currentThread() != owner) {
            System.out.println("当前线程没有持有锁, 不具备资格释放!");
            throw new IllegalArgumentException();
        }
        System.out.println(owner.getName() + " 释放了锁");
        /** 解绑 */
        owner = null;
        notify();
    }


    public static void main(String[] args) {
        NonReentrantLockByWait nonReentrantLock = new NonReentrantLockByWait();
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
