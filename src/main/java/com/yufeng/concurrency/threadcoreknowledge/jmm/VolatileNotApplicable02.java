package com.yufeng.concurrency.threadcoreknowledge.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description
 *      不适合用volatile的场景: 变量是非原子性的操作, 会导致结果不可控
 * @author yufeng
 * @create 2020-02-27
 */
public class VolatileNotApplicable02 implements Runnable {

    volatile boolean done = false;
    AtomicInteger realA = new AtomicInteger();


    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            flipDone();
            realA.incrementAndGet();
        }
    }


    private void flipDone() {
        done = !done;                   /** 有取反操作, 非原子性操作 (volatile就不适用了) */
    }


    public static void main(String[] args) throws InterruptedException {
        Runnable r =  new VolatileNotApplicable02();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(((VolatileNotApplicable02) r).done);
        System.out.println(((VolatileNotApplicable02) r).realA.get());
    }
}
