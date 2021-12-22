package com.yufeng.concurrency.juc.lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @description
 *      自旋锁
 * @author yufeng
 * @create 2020-03-18
 */
public class SpinLock {

    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock() {
        Thread current = Thread.currentThread();
        while (!sign.compareAndSet(null, current)) {
            System.out.println(Thread.currentThread().getName() + "自旋获取失败，再次尝试");   // 一直在自旋, 会有大量的开销
        }
    }


    public void unlock() {
        Thread current = Thread.currentThread();
        sign.compareAndSet(current, null);
    }


    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "开始尝试获取自旋锁");
                spinLock.lock();
                System.out.println(Thread.currentThread().getName() + "获取到了自旋锁");
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    spinLock.unlock();
                    System.out.println(Thread.currentThread().getName() + "释放了自旋锁");
                }
            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }
}
