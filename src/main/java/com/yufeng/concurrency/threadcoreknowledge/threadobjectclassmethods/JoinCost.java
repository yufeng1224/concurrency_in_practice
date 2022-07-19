package com.yufeng.concurrency.threadcoreknowledge.threadobjectclassmethods;

/**
 * @description
 *      1. 多线程的同步应用案例: join方法等待
 *      2. 以调用方角度来讲
 *         2-1 需要等待结果返回，才能继续运行的就是同步
 *         2-2 不需要等待结果返回, 就能继续运行的就是异步
 * @author yufeng
 * @create 2020-02-20
 */
public class JoinCost {

    static int r1 = 0;
    static int r2 = 0;

    public static void main(String[] args) throws InterruptedException {
        joinTest();
    }

    private static void joinTest() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                r1 = 10;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(2000);
                r2 = 20;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long end = System.currentTimeMillis();

        System.out.println(r1);
        System.out.println(r2);
        /**
         * 1. 2005, 基本都是2秒
         * 2. 说明t1, t2是在同时运行的
         * 3. t1和t2顺序颠倒也是一样的
         */
        System.out.println("cost: " + (end - start));
    }
}
