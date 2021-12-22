package com.yufeng.concurrency.juc.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description
 *      1. 演示ReentrantLock的可重入性质
 *      2. 通过lock.getHoldCount()函数演示获得锁的数量
 * @author yufeng
 * @create 2020-03-17
 */
public class GetHoldCount {

    private static ReentrantLock lock =  new ReentrantLock();

    public static void main(String[] args) {
        System.out.println(lock.getHoldCount());

        lock.lock();
        System.out.println(lock.getHoldCount());

        lock.lock();
        System.out.println(lock.getHoldCount());

        lock.lock();
        System.out.println(lock.getHoldCount());

        lock.unlock();
        System.out.println(lock.getHoldCount());

        lock.unlock();
        System.out.println(lock.getHoldCount());

        lock.unlock();
        System.out.println(lock.getHoldCount());
    }
}
