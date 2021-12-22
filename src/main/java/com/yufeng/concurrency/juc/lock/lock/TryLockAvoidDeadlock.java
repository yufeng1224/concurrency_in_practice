package com.yufeng.concurrency.juc.lock.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description
 *      1. 用tryLock()函数来避免死锁
 *      2. 原因: 获取不到则会返回false, 程序可以执行其他任务
 * @author yufeng
 * @create 2020-03-17
 */
public class TryLockAvoidDeadlock implements Runnable {

    int flag = 1;
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();


    public static void main(String[] args) {
        TryLockAvoidDeadlock r1 = new TryLockAvoidDeadlock();
        TryLockAvoidDeadlock r2 = new TryLockAvoidDeadlock();
        r1.flag = 1;
        r2.flag = 0;
        new Thread(r1).start();
        new Thread(r2).start();
    }


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (flag == 1) {
                try {
                    if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println(Thread.currentThread().getName() + "获取到了锁1");
                            Thread.sleep(new Random().nextInt(1000));
                            if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                                try {
                                    System.out.println(Thread.currentThread().getName() + "获取到了锁2");
                                    System.out.println(Thread.currentThread().getName() + "成功获取到了两把锁");
                                    break;                          // 跳出当前循环
                                } finally {
                                    lock2.unlock();
                                }
                            } else {
                                System.out.println(Thread.currentThread().getName() + "获取锁2失败, 已重试");
                            }
                        } finally {
                            lock1.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "获取锁1失败, 已重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (flag == 0) {
                try {
                    if (lock2.tryLock(3000, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println(Thread.currentThread().getName() + "获取到了锁2");
                            Thread.sleep(new Random().nextInt(1000));
                            if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                                try {
                                    System.out.println(Thread.currentThread().getName() + "获取到了锁1");
                                    System.out.println(Thread.currentThread().getName() + "成功获取到了两把锁");
                                    break;
                                } finally {
                                    lock1.unlock();
                                }
                            } else {
                                System.out.println(Thread.currentThread().getName() + "获取锁1失败，已重试");
                            }
                        } finally {
                            lock2.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "获取锁2失败，已重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}