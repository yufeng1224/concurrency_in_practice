package com.yufeng.concurrency.juc.lock.reentrantlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description
 *      通过ReentrantLock演示公平和不公平两种情况
 * @author yufeng
 * @create 2020-03-17
 */
public class FairLock {

    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();

        /** 10个线程打印 */
        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue));
        }
        for (int i = 0; i < 10; i++) {
            thread[i].start();
            try {
                Thread.sleep(100);          // 保证线程启动的顺序性
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class Job implements Runnable {

    PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "准备打印");
        printQueue.printJob();
        System.out.println(Thread.currentThread().getName() + "打印完毕");
    }
}

/**
 * 打印类
 */
class PrintQueue {

    // 公平锁
    private Lock queueLock = new ReentrantLock(true);

    // 非公平锁
    //private Lock queueLock = new ReentrantLock();


    /**
     * 演示公平锁和非公平锁的效果
     *    1. 如果是公平锁, 第一次打印完会让出锁, 给请求队列里的下一个线程使用。第二次打印会排到队列的尾部
     *    2. 如果是非公平锁, 第一次打印完接着进行第二次打印, 全部打印完毕后才交给下一个线程使用完
     *       (这样做的目的是为了提高效率, 避免唤醒带来的空档期)
     */
    public void printJob() {
        /** 第一次打印 */
        queueLock.lock();
        try {
            int duration = new Random().nextInt(10) + 1;
            System.out.println(Thread.currentThread().getName() + "进行第一次打印，需要" + duration + "秒");
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }

        /** 第二次打印 */
        queueLock.lock();
        try {
            int duration = new Random().nextInt(10) + 1;
            System.out.println(Thread.currentThread().getName() + "进行第二次打印，需要" + duration + "秒");
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }
    }
}
