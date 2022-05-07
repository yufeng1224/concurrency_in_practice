package com.yufeng.concurrency.threadcoreknowledge.synchronize.base;

/**
 * @description
 *      Synchronized可重入性质演示: 递归调用本方法
 * @author yufeng
 * @create 2020-02-21
 */
public class SynchronizedProperty01 {

    int a = 0;

    public static void main(String[] args) {
        SynchronizedProperty01 recursion = new SynchronizedProperty01();
        recursion.method1();
    }

    private synchronized void method1() {
        System.out.println("这是 method1,a = " + a);
        if (a == 0) {
            a ++;
            method1();
        }
    }

}
