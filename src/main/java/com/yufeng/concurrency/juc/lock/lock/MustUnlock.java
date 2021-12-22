package com.yufeng.concurrency.juc.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description
 *      1. Lock不像synchronized一样，异常的时候会自动释放锁
 *      2. 解决方案: 在finally代码块中释放锁，以便保证发生异常的时候锁一定被释放
 *      3. lock()方法缺点: 不能被中断, 一旦陷入死锁会陷入永久等待的情况
 * @author yufeng
 * @create 2020-03-17
 */
public class MustUnlock {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始执行任务");
        } finally {
            lock.unlock();
        }
    }
}