package com.yufeng.concurrency.threadcoreknowledge.threadunsafe;

/**
 * @description
 *      第二种线程安全问题: 死锁。
 * @author yufeng
 * @create 2020-02-25
 */
public class MultiThreadsError03 implements Runnable {

    int flag = 1;

    static Object lock1 = new Object();
    static Object lock2 = new Object();

    public static void main(String[] args) {
        MultiThreadsError03 r1 = new MultiThreadsError03();
        MultiThreadsError03 r2 = new MultiThreadsError03();
        r1.flag = 1;
        r2.flag = 0;

        new Thread(r1).start();
        new Thread(r2).start();
    }

    @Override
    public void run() {
        System.out.println("flag = " + flag);
        if (flag == 1) {
            synchronized (lock1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("1");
                }
            }
        }

        if (flag == 0) {
            synchronized (lock2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("0");
                }
            }
        }
    }
}
