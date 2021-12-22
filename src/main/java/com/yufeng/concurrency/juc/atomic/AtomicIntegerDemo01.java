package com.yufeng.concurrency.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description
 *      1. 演示AtomicInteger的基本用法
 *      2. 对比非原子类的线程安全问题, 使用了原子类之后, 不需要加锁也可以保证线程安全
 * @author yufeng
 * @create 2020-03-20
 */
public class AtomicIntegerDemo01 implements Runnable {

    private static final AtomicInteger atomicInteger = new AtomicInteger();

    private static volatile int basicCount = 0;

    public void incrementAtomic() {
        atomicInteger.getAndIncrement();
        //atomicInteger.getAndAdd(-90);
    }


    public static void getAndAdd() {
        atomicInteger.getAndAdd(1000);
    }


    /**
     * 普通变量需要通过synchronized来保证线程安全
     */
    public void incrementBasic() {
        basicCount ++;
    }


    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementAtomic();
            incrementBasic();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo01 r = new AtomicIntegerDemo01();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("原子类的结果：" + atomicInteger.get());
        System.out.println("普通变量的结果：" + basicCount);

        getAndAdd();
        System.out.println("原子类的结果：" + atomicInteger.get());
    }
}
