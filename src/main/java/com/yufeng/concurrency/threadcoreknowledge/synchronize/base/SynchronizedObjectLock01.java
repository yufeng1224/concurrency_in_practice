package com.yufeng.concurrency.threadcoreknowledge.synchronize.base;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      对象锁用法(一): 代码块形式, 手动指定锁对象
 *         1. 默认对象为当前实例对象
 *         2. 使用synchronized 关键字后，一次只能执行一个对象
 * @author yufeng
 * @create 2020-02-21
 */
public class SynchronizedObjectLock01 extends BaseMain implements Runnable {

    static SynchronizedObjectLock01 instance = new SynchronizedObjectLock01();

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("对象锁的代码块形式, 当前线程: " + Thread.currentThread().getName());

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "运行结束");
        }
    }


    public static void main(String[] args) {
        executeShow(instance);
    }
}
