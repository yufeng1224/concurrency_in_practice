package com.yufeng.concurrency.threadcoreknowledge.stopthread;

/**
 * @description
 *     注意点:  Thread.interrupted()方法的目标对象是"当前线程"， 而不管调用方来自于哪个对象
 * @author yufeng
 * @create 2020-02-17
 */
public class RightWayInterrupted {

    public static void main(String[] args) {

        Thread threadOne = new Thread(() -> {
            for (; ;) {}
        });
        threadOne.start();

        /** 中断目标线程 */
        threadOne.interrupt();

        /** 获取中断标志 */
        System.out.println("thread isInterrupted: " + threadOne.isInterrupted());                        // true

        /** 中断当前主线程 */
        Thread.currentThread().interrupt();

        /** 获取主线程中断标志后并清除该状态 (threadOne不起任何效果)*/
        System.out.println("main isInterrupted: " + threadOne.interrupted());                            // true
        /** 再次获取主线程中断标志 */
        System.out.println("main isInterrupted: " + Thread.currentThread().isInterrupted());             // false

        /** 再次获取目标线程中断标志 */
        System.out.println("thread isInterrupted: " + threadOne.isInterrupted());                        // true
    }
}
