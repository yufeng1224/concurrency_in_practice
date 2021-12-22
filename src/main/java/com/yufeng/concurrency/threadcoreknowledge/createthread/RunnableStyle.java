package com.yufeng.concurrency.threadcoreknowledge.createthread;

/**
 * @description
 *      多线程实现方式之一: 实现Runnable接口方式
 * @author yufeng
 * @create 2020-02-14
 */
public class RunnableStyle implements Runnable{

    @Override
    public void run() {
        System.out.println("实现Runnable方式实现线程");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }
}
