package com.yufeng.concurrency.threadcoreknowledge.createthread;

/**
 * @description
 *      多线程实现方式之一: 继承Thread类方式
 * @author yufeng
 * @create 2020-02-14
 */
public class ThreadStyle extends Thread {

    @Override
    public void run() {
        System.out.println("用Thread类实现多线程");
    }

    public static void main(String[] args) {
        new ThreadStyle().start();
    }
}
