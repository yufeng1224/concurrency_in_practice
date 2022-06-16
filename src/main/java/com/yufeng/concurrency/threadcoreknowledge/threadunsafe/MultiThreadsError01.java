package com.yufeng.concurrency.threadcoreknowledge.threadunsafe;


/**
 * @description
 *      1. 线程安全问题: a++运行结果出错演示
 *      2. 原因: 在没有充足同步的情况下, 多个线程中的操作执行顺序是不可预测的,
 *         线程之间的交替操作会导致不可预测的结果!
 * @author yufeng
 * @create 2020-02-25
 */
public class MultiThreadsError01 implements Runnable {

    int index = 0;

    static MultiThreadsError01 instance = new MultiThreadsError01();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("表面上结果是: " + instance.index);
    }

    /**
     * index ++ 看上去是单个操作，实际上是3个步骤的复合操作(read-modify-write)
     *   步骤1: 读取index的值;
     *   步骤2: 将index+1;
     *   步骤3: 计算结果写入index;
     *
     * 由于运行时可能将多个线程之间的操作交替执行, 因此这两个线程可能同时执行读操作,
     * 从而使得它们可能得到相同的值, 并都将这个值加1。结果就是, 在不同线程的调用中返
     * 回了相同的数值!
     */
    @Override
    public void run() {
        for (int i = 0; i < 10000; i ++) {
            index ++;
        }
    }
}
