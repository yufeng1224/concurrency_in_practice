package com.yufeng.concurrency.threadcoreknowledge.threadobjectclassmethods;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description
 *      1. 思考: wait()、notify()、notifyAll()方法可不可以在lock中使用?
 *      2. 结论: 不行, 只能在synchronized修饰的代码块中执行, 会抛出异常IllegalMonitorStateException
 * @author yufeng
 * @create 2020-02-20
 */
public class LockCantUseWait implements Runnable {

    private static Lock lock = new ReentrantLock();

    private static Object lockA = new Object();

    private int flag = 1;

    public static void main(String[] args) {
        LockCantUseWait r1 = new LockCantUseWait();
        new Thread(r1).start();

        LockCantUseWait r2 = new LockCantUseWait();
        r2.flag = 0;
        new Thread(r2).start();
    }

    @Override
    public void run() {
        if (flag == 1) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "开始执行任务");
                lockA.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        if (flag == 0) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "开始执行任务");
                lockA.notify();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
