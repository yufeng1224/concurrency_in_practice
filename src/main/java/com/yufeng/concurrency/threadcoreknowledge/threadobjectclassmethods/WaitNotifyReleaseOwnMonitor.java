package com.yufeng.concurrency.threadcoreknowledge.threadobjectclassmethods;

import java.util.concurrent.TimeUnit;

/**
 * @descrption
 *      多层synchronized嵌套, 则wait只释放当前的对象锁
 * @author yufeng
 * @create 2020-02-20
 */
public class WaitNotifyReleaseOwnMonitor {

    /** 创建两个对象锁 */
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("ThreadA got resourceA lock");
                synchronized (resourceB) {
                    System.out.println("ThreadA got resourceB lock.");
                    try {
                        System.out.println("ThreadA releases resourceA lock.");
                        resourceA.wait();               // 释放resourceA锁, 所以thread2能进入resourceA的synchronized代码块
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (resourceA) {
                System.out.println("ThreadB got resourceA lock.");
                System.out.println("ThreadB tries to resourceB lock.");

                synchronized (resourceB) {
                    System.out.println("ThreadB got resourceB lock.");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}