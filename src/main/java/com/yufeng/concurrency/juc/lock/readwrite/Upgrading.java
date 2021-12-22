package com.yufeng.concurrency.juc.lock.readwrite;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description
 *      1. 演示ReentrantReadWriteLock可以降级, 不能升级
 *      2. 升级会导致程序阻塞
 *      3. 原因: 假设线程A和线程B都需要升级, A升级要求B释放读锁, B升级要求A释放读锁, 这样就造成了相互等待
 * @author yufeng
 * @create 2020-03-18
 */
public class Upgrading {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(
            false);
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();


    private static void readUpgrading() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了读锁，正在读取");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("升级不会成功, 会带来阻塞");
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + "获取到了写锁，升级成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            readLock.unlock();
        }
    }


    private static void writeDowngrading() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了写锁，正在写入");
            TimeUnit.SECONDS.sleep(1);
            readLock.lock();                // 拿到了读锁, 这样就降级了!
            System.out.println("在不释放写锁的情况下，直接获取读锁，成功降级");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            writeLock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("先演示降级是可以的");
        Thread thread1 = new Thread(() -> writeDowngrading(), "Thread1");
        thread1.start();
        thread1.join();
        System.out.println("------------------");
        System.out.println("演示升级是不行的");

        Thread thread2 = new Thread(() -> readUpgrading(), "Thread2");
        thread2.start();
    }
}
