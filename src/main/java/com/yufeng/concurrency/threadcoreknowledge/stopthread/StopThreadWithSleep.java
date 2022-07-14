package com.yufeng.concurrency.threadcoreknowledge.stopthread;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *     1. 当线程进入阻塞过程中, 能够响应中断。
 *     2. 线程收到信号会以抛出异常的方式来中断当前的线程, 并且清除中断的标记
 *     3. 涉及到的方法有: Object 类中的wait(), wait(long), or wait(long, int)
 *        Thread 类中的join(), join(long), join(long, int), sleep(long), sleep(long, int),
 * @author yufeng
 * @create 2021-02-17
 */
public class StopThreadWithSleep implements Runnable{

    @Override
    public void run() {
        int num = 0;

        try {
            while (num <= 300) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的整数");
                }
                num ++;
            }

            TimeUnit.SECONDS.sleep(2);
            System.out.println("线程处于TIMED_WAITING状态, 观察程序响应中断后是否会执行当前这行");         // 程序不会继续执行到这行
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            /** 中断标记为被清除 */
            System.out.println("当前线程的中断状态: " + Thread.currentThread().isInterrupted());     // false
        }
    }

    public static void main(String[] args)throws InterruptedException {
        Thread thread =  new Thread(new StopThreadWithSleep());
        thread.start();

        Thread.sleep(500);
        System.out.println("thread的状态: " + thread.getState());
        thread.interrupt();
    }

}
