package com.yufeng.concurrency.threadcoreknowledge.singleton;

/**
 * @description
 *      单例模式实现方式(一):
 *         1. 饿汉式 ———— 静态常量写法
 *         2. 类加载的时候就完成了实例化, 由JVM保证了线程安全
 * @author yufeng
 * @create 2020-03-02
 */
public class Singleton1 {

    private final static Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {}

    public static Singleton1 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        getInstance();
    }
}
