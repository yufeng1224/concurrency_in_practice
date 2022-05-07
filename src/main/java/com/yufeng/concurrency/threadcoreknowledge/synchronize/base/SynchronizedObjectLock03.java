package com.yufeng.concurrency.threadcoreknowledge.synchronize.base;

import java.util.concurrent.TimeUnit;

/**
 * @descrpiton
 *      对象锁用法(二): 方法锁形式
 * @author yufeng
 * @create 2020-02-21
 */
public class SynchronizedObjectLock03 extends BaseMain implements Runnable {

    private static SynchronizedObjectLock03 instance = new SynchronizedObjectLock03();

    @Override
    public void run() {
        method();
    }

    public synchronized void method() {
        System.out.println("对象锁的方法修饰符形式, 当前线程: " + Thread.currentThread().getName());

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
