package com.yufeng.concurrency.juc.cas;

/**
 * @description
 *      1. 两个线程竞争
 *      2. 预期值正确的线程修改, 不正确的线程不做修改
 * @author yufeng
 * @create 2020-03-22
 */
public class TwoThreadsCompetition implements Runnable {

    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            System.out.println(Thread.currentThread().getName() + "进行修改操作");
            value = newValue;
        }
        return oldValue;
    }


    @Override
    public void run() {
        compareAndSwap(0, 1);
    }


    public static void main(String[] args) throws InterruptedException {
        TwoThreadsCompetition r = new TwoThreadsCompetition();
        r.value = 0;

        Thread t1 = new Thread(r,"Thread-1");
        Thread t2 = new Thread(r,"Thread-2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(r.value);
    }
}
