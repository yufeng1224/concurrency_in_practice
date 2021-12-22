package com.yufeng.concurrency.juc.lock.reentrantlock;

/**
 * @description
 *      1. synchronized具备可重入性质演示
 * @author yufeng
 * @create 2020-03-17
 */
public class ReentrantProperty {

    public synchronized void method1() {
        System.out.println(Thread.currentThread().getName() + " method1");
        method2();
    }

    public synchronized void method2() {
        System.out.println(Thread.currentThread().getName() + " method2");
    }

    public static void main(String[] args) {
        new ReentrantProperty().method1();
    }
}
