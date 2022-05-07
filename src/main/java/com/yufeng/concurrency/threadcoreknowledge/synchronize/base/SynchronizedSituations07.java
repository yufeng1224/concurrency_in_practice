package com.yufeng.concurrency.threadcoreknowledge.synchronize.base;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      方法抛出异常后, 会释放锁
 * @author yufeng
 * @create 2020-02-21
 */
public class SynchronizedSituations07 extends BaseMain implements Runnable {

    static SynchronizedSituations07 instance = new SynchronizedSituations07();

    @Override
    public void run() {
        if ("t1".equals(Thread.currentThread().getName())) {
            method1();
        } else {
            method2();
        }
    }

    public synchronized void method1() {
        System.out.println("抛出异常的同步方法。当前线程名" + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public synchronized void method2() {
        System.out.println("正常运行的同步方法。当前线程名" + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }

    public static void main(String[] args) {
        executeShow(instance);
    }
}
