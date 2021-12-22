package com.yufeng.concurrency.threadcoreknowledge.synchronize.advanced;

/**
 * 描述:
 *      消失的请求数
 *      不使用并发手段的后果演示
 * @author yufeng
 * @create 2020-02-19
 */
public class DisappearRequest implements Runnable {

    static DisappearRequest instance = new DisappearRequest();

    static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(count);
    }


    @Override
    public  void run() {
        for (int i = 0; i < 100000; i ++) {
            count ++;
        }
    }
}
