package com.yufeng.concurrency.threadcoreknowledge.sixstates;

/**
 * @description
 *      1. 展示线程的NEW、RUNNABLE、TERMINATED 状态。
 *      2. 即使是正在运行, 也是Runnable状态, 而不是Running。
 * @author yufeng
 * @create 2020-02-19
 */
public class NewRunnableTerminated implements Runnable {

    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableTerminated());
        System.out.println(thread.getState());       /** 打印出NEW的状态 */

        thread.start();
        System.out.println(thread.getState());       /** 打印出RUNNABLE的状态 */

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getState());       /** 正在运行的线程, 是RUNNABLE的状态 */

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getState());       /** 打印出TERMINATED状态 */
    }


    @Override
    public void run() {
        for (int i = 0; i < 1000; i ++) {
            System.out.println(i);
        }
    }
}
