package com.yufeng.concurrency.threadcoreknowledge.stopthread.wrongways;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      演示用volatile的局限: 看似可行
 * @author yufeng
 * @create 2020-02-17
 */
public class WrongWayVolatile implements Runnable {

    private volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数。");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        WrongWayVolatile r = new WrongWayVolatile();
        Thread thread = new Thread(r);
        thread.start();
        TimeUnit.SECONDS.sleep(5);
        r.canceled = true;
    }
}
