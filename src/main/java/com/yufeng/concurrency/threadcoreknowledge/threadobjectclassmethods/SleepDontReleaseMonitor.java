package com.yufeng.concurrency.threadcoreknowledge.threadobjectclassmethods;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. 在synchronized修饰的代码块或方法中,  线程sleep的时候不会释放synchronized的monitor,
 *      2. 等sleep时间到了以后, 正常结束后才会释放锁。
 * @author yufeng
 * @create 2020-02-20
 */
public class SleepDontReleaseMonitor implements Runnable {

    public static void main(String[] args) {
        SleepDontReleaseMonitor monitor = new SleepDontReleaseMonitor();
        new Thread(monitor, "thread-0").start();
        new Thread(monitor, "thread-1").start();
    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        System.out.println("线程" + Thread.currentThread().getName() + "获取到了 monitor");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程" + Thread.currentThread().getName() + "退出了同步代码块");
    }
}
