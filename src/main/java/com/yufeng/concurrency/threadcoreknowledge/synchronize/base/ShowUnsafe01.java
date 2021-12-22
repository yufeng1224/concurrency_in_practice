package com.yufeng.concurrency.threadcoreknowledge.synchronize.base;

/**
 * @description
 *      演示不使用并发手段的后果
 * @author yufeng
 * @create 2020-02-21
 */
public class ShowUnsafe01 implements Runnable {

    static ShowUnsafe01 r = new ShowUnsafe01();

    static int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            count ++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }
}
