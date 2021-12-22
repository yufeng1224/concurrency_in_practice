package com.yufeng.concurrency.threadcoreknowledge.startthread;

/**
 *@description
 *      1. 多个线程调用start()函数, 执行顺序观察
 *      2. 结论: 线程的运行是乱序的, start()函数的调用顺序并不会决定线程的执行顺序!
 * @author yufeng
 * @create 2020-02-14
 */
public class MultipleThreadStart implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i ++) {
            System.out.println("当前执行线程为: " + Thread.currentThread().getName());
        }
    }


    public static void main(String[] args) throws Exception {
        MultipleThreadStart runnable = new MultipleThreadStart();

        Thread thread0 = new Thread(runnable);
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        Thread.sleep(1000);

        thread0.start();
        thread1.start();
        thread2.start();
    }
}
