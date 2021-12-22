package com.yufeng.concurrency.juc.lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description
 *     演示非公平和公平的ReentrantReadWriteLock的策略
 * @author yufeng
 * @create 2020-03-18
 */
public class NonfairBargeDemo {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);

    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private static void read() {
        System.out.println(Thread.currentThread().getName() + "开始尝试获取读锁");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到读锁, 正在读取");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            readLock.unlock();
        }
    }

    private static void write() {
        System.out.println(Thread.currentThread().getName() + "开始尝试获取写锁");
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到写锁, 正在写入");
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            writeLock.unlock();
        }
    }


    public static void main(String[] args) {
        new Thread(()->write(),"Thread_1").start();
        new Thread(()->read(), "Thread_2").start();

        /**
         * 非公平策略下
         *   1. 头结点是读线程, 子线程创建的读线程是可以插队的! 说明读取是可以插队的
         *      (比如thread-2拿到读锁, 子线程创建的读线程也可以竞争获得读锁)
         *   2. 但是, 如果当线程3一旦拿到读锁, 则队列中的头结点Thread-4是写锁。则后面没有竞争到读锁的子线程就不允许插队了!
         *   3. 直到之前释放的读锁全部释放, 然后再执行线程4的写锁!
         *   4. 当Thread-4写入完成后, 后面的读锁还可以一起执行
         *
         * 公平策略下
         *   1. 绝对保证排队的顺序
         *   2. Thread-1写锁执行
         *   3. Thread-2和Thread-3读锁可以同时执行
         *   4. Thread-4写锁执行
         *   5. Thread-5、Thread-6以及后面的子线程读锁可以同时执行
         */
        new Thread(()->read(), "Thread_3").start();
        new Thread(()->write(),"Thread_4").start();
        new Thread(()->read(), "Thread_5").start();

        // 创造插队的读线程
        new Thread(() -> {
            Thread thread[] = new Thread[1000];
            for (int i = 0; i < 1000; i++) {
                thread[i] = new Thread(() -> read(), "子线程创建的Thread" + i);
            }
            for (int i = 0; i < 1000; i++) {
                thread[i].start();
            }
        }).start();
    }
}
