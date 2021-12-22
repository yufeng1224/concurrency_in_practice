package com.yufeng.concurrency.juc.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description
 *      1. 可重入锁的应用
 *      2. 可重入锁具备递归调用自己的能力
 * @author yufeng
 * @create 2020-03-17
 */
public class RecursionDemo {

    private static ReentrantLock lock = new ReentrantLock();

    private static void accessResource() {
        lock.lock();
        try {
            System.out.println("已经对资源进行了处理");
            if (lock.getHoldCount() < 5) {
                System.out.println(lock.getHoldCount());
                accessResource();                           // 递归调用
                System.out.println(lock.getHoldCount());
            }
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        accessResource();
    }
}
