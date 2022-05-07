package com.yufeng.concurrency.threadcoreknowledge.synchronize.base;

/**
 * @description
 *      Synchronized 可重入性质演示: 调用不同的同步方法
 * @author yufeng
 * @create 2020-02-21
 */
public class SynchronizedProperty02 {

    private synchronized  void method1() {
        System.out.println("我是method1");
        method2();
    }

    private synchronized void method2() {
        System.out.println("我是method2");
    }

    public static void main(String[] args) {
        SynchronizedProperty02 synchronizedOtherMethod = new SynchronizedProperty02();
        synchronizedOtherMethod.method1();
    }

}
