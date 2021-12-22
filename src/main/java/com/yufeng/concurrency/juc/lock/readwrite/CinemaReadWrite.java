package com.yufeng.concurrency.juc.lock.readwrite;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description
 *      电影院的升级操作
 * @author yufeng
 * @create 2020-03-18
 */
public class CinemaReadWrite {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    /**
     * 静态内部类创建的应用!
     * ReadLock和WriteLock是ReentrantReadWriteLock的静态内部类。
     */
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    /**
     * 读取
     */
    private static void read() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了读锁，正在读取");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            readLock.unlock();
        }
    }

    /**
     * 写锁
     */
    private static void write() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了写锁，正在写入");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            writeLock.unlock();
        }
    }

    /**
     * 读写锁案例演示:
     *      Thread1得到了读锁，正在读取
     *      Thread2得到了读锁，正在读取
     *      Thread1释放读锁
     *      Thread2释放读锁
     *      Thread3得到了写锁，正在写入
     *      Thread3释放写锁
     *      Thread4得到了写锁，正在写入
     *      Thread4释放写锁
     * @param args
     */
    public static void main(String[] args) {
        new Thread(()->read(),"Thread1").start();
        new Thread(()->read(),"Thread2").start();
        new Thread(()->write(),"Thread3").start();
        new Thread(()->write(),"Thread4").start();
    }
}
