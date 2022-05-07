package com.yufeng.concurrency.threadcoreknowledge.threadobjectclassmethods;

import java.util.concurrent.TimeUnit;

/**
 * @descrption
 *     使用synchronized通信交替打印奇偶数
 * @author yufeng
 * @create 2020-02-20
 *
 */
public class WaitNotifyPrintOddEvenSyn {

    private static int count;
    private static final Object lock = new Object();

    /** 总共操作次数, 用于演示浪费的操作 */
    private static int countOp = 1;

    /**
     * 1. 两个线程交替打印 0~100 的奇偶数，用synchronized关键字实现通信
     * 2. 当奇数线程打印完奇数后, 两个线程竞争锁, 奇数线程可能会再次获得锁, 只不过这次进入方法后不再进行打印
     * 3. 这种实现方式存在很多的浪费操作(资源损耗)
     */
    public static void main(String[] args) {
        new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    countOp ++;                 // 统计总共执行的次数
                    if ((count & 1) == 0) {     // 位运算提高效率,  count%2 == 0 (效率偏低)
                        System.out.println(Thread.currentThread().getName() + ":" + count++);
                    }
                }
            }
        }, "偶数").start();

        new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    countOp ++;
                    if ((count & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + ":" + count++);
                    }
                }
            }

        }, "奇数").start();

        /** 保证统计次数正确 */
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("总共执行的操作次数, countOp = " + countOp);
    }
}