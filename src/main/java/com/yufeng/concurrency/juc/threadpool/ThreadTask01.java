package com.yufeng.concurrency.juc.threadpool;

/**
 * @description
 *      新建一个线程代码演示
 * @author yufeng
 * @create 2020-03-10
 */
public class ThreadTask01 {

    public static void main(String[] args) {
        Thread thread = new Thread(new Task());
        thread.start();
    }
}

class Task implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " - 执行了任务");
    }
}
