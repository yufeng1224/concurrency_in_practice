package com.yufeng.concurrency.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @description
 *      1. 演示原子数组的使用方法
 * @author yufeng
 * @create 2020-03-20
 */
public class AtomicIntegerArrayDemo01 {

    public static void main(String[] args) {
        /** 里面的1000个整型都可以被原子的更新 */
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);

        Incrementer incrementer = new Incrementer(atomicIntegerArray);
        Decrementer decrementer = new Decrementer(atomicIntegerArray);

        Thread[] threadsIncrementer = new Thread[100];
        Thread[] threadsDecrementer = new Thread[100];

        for (int i = 0; i < 100; i++) {
            threadsDecrementer[i] = new Thread(decrementer);
            threadsIncrementer[i] = new Thread(incrementer);
            threadsDecrementer[i].start();
            threadsIncrementer[i].start();
        }

        for (int i = 0; i < 100; i++) {
            try {
                threadsDecrementer[i].join();
                threadsIncrementer[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            if (atomicIntegerArray.get(i) != 0) {
                System.out.println("发现了错误" + i);
            }
            // System.out.println(atomicIntegerArray.get(i));
        }
        System.out.println("运行结束");
    }
}


class Incrementer implements Runnable {

    private AtomicIntegerArray array;

    public Incrementer(AtomicIntegerArray atomicIntegerArray) {
        this.array = atomicIntegerArray;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndIncrement(i);
        }
    }
}


class Decrementer implements Runnable {

    private AtomicIntegerArray array;

    public Decrementer(AtomicIntegerArray atomicIntegerArray) {
        this.array = atomicIntegerArray;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndDecrement(i);
        }
    }
}



