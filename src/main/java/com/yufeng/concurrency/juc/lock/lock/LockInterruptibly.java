package com.yufeng.concurrency.juc.lock.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description
 *     1. lockInterruptibly()函数功能演示
 *     2. lockInterruptibly()作用: 在等待锁的过程中, 线程可以被中断
 *     3. 案例情况: thread1没有获得到锁, 在等锁期间被打断
 * @author yufeng
 * @create 2020-03-17
 */
public class LockInterruptibly implements Runnable {

    private Lock lock = new ReentrantLock();

    private Object waitLock = new Object();

    public static void main(String[] args) {
        LockInterruptibly lockInterruptibly = new LockInterruptibly();
        Thread thread0 = new Thread(lockInterruptibly);
        Thread thread1 = new Thread(lockInterruptibly);

        thread0.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.start();

        thread0.interrupt();

        /** 使用lockInterruptibly()函数, 线程在等待获取锁的期间即可被中断 */
        //thread1.interrupt();
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "尝试获取锁");
        try {
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName() + "获取到了锁");
                /** wait()必须写在Synchronized修饰的代码块内 */
                // waitLock.wait();
                /** 获得到锁的线程进入TIMED_WAITING状态, 可以响应中断 */
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "睡眠期间被中断了");
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放了锁");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "等待锁期间被中断了");
        }
    }
}