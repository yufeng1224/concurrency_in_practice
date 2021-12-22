package com.yufeng.concurrency.threadcoreknowledge.startthread;

/**
 * @description
 *     对比start和run两种启动线程的方式
 * @author yufeng
 * @create 2020-02-14
 */
public class StartAndRunMethod {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());

        /** run 方法相当于主线程调用一个普通方法 */
        runnable.run();                         // main
        /** start 才是真正意义上启动一个线程 */
        new Thread(runnable).start();           // Thread-0
    }

}
