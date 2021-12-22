package com.yufeng.concurrency.juc.lock.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description
 *      1. 演示多线程预定电影院座位
 *      2. 任何两个线程都无法同时预订同一个座位
 * @author yufeng
 * @create 2020-03-17
 */
public class CinemaBookSeat {

    private static ReentrantLock lock = new ReentrantLock();

    private static void bookSeat() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始预定座位");
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + "完成预定座位");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        /** 以下为同一个座位的场景演示 */
        new Thread(() -> bookSeat()).start();
        new Thread(() -> bookSeat()).start();
        new Thread(() -> bookSeat()).start();
        new Thread(() -> bookSeat()).start();
    }
}
