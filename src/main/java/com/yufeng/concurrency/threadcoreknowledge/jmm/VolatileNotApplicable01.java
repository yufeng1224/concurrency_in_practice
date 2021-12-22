package com.yufeng.concurrency.threadcoreknowledge.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description
 *      不适合用volatile的场景: a++
 * @author yufeng
 * @create 2020-02-27
 */
public class VolatileNotApplicable01 implements Runnable {

    volatile int a;
    AtomicInteger realA = new AtomicInteger();


    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            a ++;
            realA.incrementAndGet();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Runnable r =  new VolatileNotApplicable01();

        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(((VolatileNotApplicable01) r).a);
        System.out.println(((VolatileNotApplicable01) r).realA.get());
    }

}
