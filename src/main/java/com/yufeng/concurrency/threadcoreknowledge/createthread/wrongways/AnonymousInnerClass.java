package com.yufeng.concurrency.threadcoreknowledge.createthread.wrongways;

/**
 * @description
 *      1. 错误观点: 匿名内部类也是一种新建线程的方式
 *      2. 错误原因: 只是简化了写法, 实现多线程的方式没变
 * @author yufeng
 * @create 2020-02-14
 */
public class AnonymousInnerClass {

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
    }

}
