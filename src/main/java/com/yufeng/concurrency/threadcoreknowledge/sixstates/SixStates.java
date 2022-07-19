package com.yufeng.concurrency.threadcoreknowledge.sixstates;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      线程6种状态演示
 * @author yufeng
 * @create 2020-02-19
 */
public class SixStates {

    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                System.out.println("running...");
            }
        };

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                while (true) {                      // runnable

                }
            }
        };
        t2.start();

        Thread t3 = new Thread("t3") {
            @Override
            public void run() {
                System.out.println("running...");
            }
        };
        t3.start();

        Thread t4 = new Thread("t4") {
            @Override
            public void run() {
                synchronized (SixStates.class) {
                    try {
                        Thread.sleep(1000000);     // timed_waiting
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t4.start();

        Thread t5 = new Thread("t5") {
            @Override
            public void run() {
                try {
                    t2.join();                          // waiting
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t5.start();

        Thread t6 = new Thread("t6") {
            @Override
            public void run() {
                synchronized (SixStates.class) {        // blocked
                    try {
                        TimeUnit.SECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t6.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("t1 state： " + t1.getState());
        System.out.println("t2 state： " + t2.getState());
        System.out.println("t3 state： " + t3.getState());
        System.out.println("t4 state： " + t4.getState());
        System.out.println("t5 state： " + t5.getState());
        System.out.println("t6 state： " + t6.getState());
    }

}
