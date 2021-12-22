package com.yufeng.concurrency.threadcoreknowledge.createthread.wrongways;

/**
 * @description
 *      错误观点: lambda 表达式是一种新建线程的方式
 *      错误原因: 只是简化了写法, 实现多线程的方式没变;
 * @author yufeng
 * @create 2020-02-14
 */
public class Lambda {

    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
