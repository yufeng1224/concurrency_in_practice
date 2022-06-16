package com.yufeng.concurrency.threadcoreknowledge.threadunsafe;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description
 *      1. a++运行结果错误演示
 *      2. 进阶: 统计出错的位置以及出错的次数
 * @author yufeng
 * @create 2020-02-25
 */
public class MultiThreadsError02 implements Runnable {

    int index = 0;

    final boolean[] marked = new boolean[100000];

    static MultiThreadsError02 instance = new MultiThreadsError02();

    static AtomicInteger trueCount  = new AtomicInteger();      // 用于统计真实的相加次数
    static AtomicInteger wrongCount = new AtomicInteger();      // 用于统计重叠错误的相加次数

    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("并发操作下结果是: " + instance.index);
        System.out.println("真正运行的次数是: " + trueCount);
        System.out.println("错误运行的次数是: " + wrongCount);
    }

    @Override
    public void run() {
        marked[0] = true;

        for (int i = 0; i < 10000; i ++) {
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            index ++;

            try {
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            trueCount.incrementAndGet();

            synchronized (instance) {
                if (marked[index] && marked[index - 1]) {       // 可见性判断
                    System.out.println("发生错误,位置:" + index);
                    wrongCount.incrementAndGet();
                }
                marked[index] = true;
            }
        }
    }
}
