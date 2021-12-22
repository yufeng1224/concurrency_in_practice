package com.yufeng.concurrency.threadcoreknowledge.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description
 *      适用volatile的场景: 只存在赋值之类的原子性操作
 * @author yufeng
 * @create 2020-02-27
 */
public class VolatileApplicable01 implements Runnable {

    volatile boolean done = false;
    AtomicInteger realA = new AtomicInteger();


    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            setDone();
            realA.incrementAndGet();
        }
    }


    private void setDone() {
        done = true;
    }


    public static void main(String[] args) throws InterruptedException {
        Runnable r =  new VolatileApplicable01();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(((VolatileApplicable01) r).done);
        System.out.println(((VolatileApplicable01) r).realA.get());
    }
}
