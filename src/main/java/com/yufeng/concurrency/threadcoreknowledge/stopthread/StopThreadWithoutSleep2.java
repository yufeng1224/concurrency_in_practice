package com.yufeng.concurrency.threadcoreknowledge.stopthread;

/**
 * @description
 *      普通情况演示(二)
 *         1. 当run()方法内没有sleep()或者wait()方法时;
 *         2. run()方法内添加 Thread.currentThread().isInterrupted()方法,
 *            来判断是否需要被中断, 如果被中断则不再继续执行run()方法内的业务逻辑;
 *         3. 结论: 目标线程接收到中断通知, 能够停止正在运行的任务;
 * @author yufeng
 * @create 2021-02-17
 */
public class StopThreadWithoutSleep2 implements Runnable{

    @Override
    public void run() {
        int num = 0;

        /**
         * 当run()方法内添加isInterrupted()方法后, 当前线程就能够根据主线程的interrupted通知,
         * 自行判断是否停止运行任务
         */
        while(!Thread.currentThread().isInterrupted() && num < Integer.MAX_VALUE / 2) {
            if (num % 1000 == 0) {
                System.out.println(num + "是1000的倍数");
            }
            num ++;
        }

        System.out.println("任务运行结束了");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new StopThreadWithoutSleep2());
        thread.start();
        Thread.sleep(1000);

        thread.interrupt();
    }

}
