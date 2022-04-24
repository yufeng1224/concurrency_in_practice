package com.yufeng.concurrency.threadcoreknowledge.sixstates;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *     展示 BLOCKED, WAITING, TIMED_WAITING 3种线程状态
 * @author yufeng
 * @create 2020-02-19
 */
public class BlockedWaitingTimedWaiting implements Runnable {

    public static void main(String[] args) {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();

        Thread thread0 = new Thread(runnable);
        Thread thread1 = new Thread(runnable);
        thread0.start();
        thread1.start();

        /** thread0 先进入RUNNABLE状态 */
        System.out.println("thread0的状态:" + thread0.getState());

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * 1. thread0 执行Thread.sleep(1000), 线程进入TIMED_WAITING状态
         * 2. thread1 由于无法获得sync()的锁, 处于BLOCKED 状态
         */
        System.out.println("thread0的状态:" + thread0.getState());
        System.out.println("thread1的状态:" + thread1.getState());

        try {
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /** thread0 执行了wait(), 释放了监视器锁, 并进入WAITING状态 */
        System.out.println("thread0的状态:" + thread0.getState());

        /**
         *  thread1 拿到同步锁, 从BLOCKED -> RUNNABLE状态,
         *  由于又立即执行了 Thread.sleep(1000),
         *  从RUNNABLE -> TIMED_WAITING状态
         */
        System.out.println("thread1的状态:" + thread1.getState());
    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        System.out.println("当前执行的线程: " + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(1);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
