package com.yufeng.concurrency.threadcoreknowledge.threadunsafe;

/**
 * 描述:
 *      即使代码中不显示创建线程，在运行main时，JVM也会自动启动其他的线程，用断点debug查看
 * @author yufeng
 * @create 2020-02-25
 */
public class JavaAndThreads {

    public static void main(String[] args) {
        System.out.println("Hello Threads!");
    }

}
