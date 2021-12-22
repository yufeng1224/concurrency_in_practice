package com.yufeng.concurrency.threadcoreknowledge.threadobjectclassmethods;

import java.util.concurrent.TimeUnit;

/**
 * @descrption
 *      两个线程交替打印0 ~ 100的奇偶数, 使用wait和notify线程通信的方式
 *      这样效率更高，不会有浪费的操作
 * @author yufeng
 * @create 2020-02-20
 */
public class WaitNotifyPrintOddEveWait {

    private static int count = 0;

    private static int countOp = 0;

    private static final Object lock = new Object();

    public static void main(String[] args) throws Exception {
        new Thread(new TurningRunner(), "偶数").start();
        Thread.sleep(10);                                       /** 防止奇偶线程错乱 */

        new Thread(new TurningRunner(), "奇数").start();

        TimeUnit.SECONDS.sleep(2);                             /** 保证主线程最后关闭 */
        System.out.println("总共操作的次数, countOp=" + countOp);        /** 不会存在浪费的操作 */
    }


    /**
     * 拿到锁，我们就打印
     * 打印完，唤醒其他线程，自己就休眠
     */
    static class TurningRunner implements Runnable {

        @Override
        public void run() {
            while (count <= 100) {
                countOp ++;
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + ":" + count ++);  /** 拿到锁就打印 */
                    lock.notify();
                    if (count <= 100) {
                        try {
                            lock.wait();                                /** 如果任务还没结束, 就让出当前的锁, 并休眠 */
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
