package com.yufeng.concurrency.threadcoreknowledge.createthread;

/**
 * @description
 *     1. 同时使用Runnable和Thread两种实现线程的方式, 执行哪个方法?
 *     2. 结论: 执行的是覆盖Thread类的run()方法
 * @author yufeng
 * @create 2020-02-14
 */
public class BothRunnableThread {

    public static void main(String[] args) {
        new Thread(() ->
            System.out.println("我来自Runnable")
        ){
            /** 重写的方法覆盖了Runnable()中的run() */
            @Override
            public void run() {
                System.out.println("我来自Thread");
            }
        }.start();
    }
}
