package com.yufeng.concurrency.threadcoreknowledge.singleton;

/**
 * @description
 *      单例实现方式(四)
 *         1. 懒汉式
 *         2. 线程安全
 *            缺点是效率低。比如多线程同时处理字符串业务, 这种操作方式效率低下
 * @author yufeng
 * @create 2020-03-02
 */
public class Singleton4 {

    private static Singleton4 instance;

    private Singleton4() {}

    public static synchronized Singleton4 getInstance() {
        if (instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }

    public static void main(String[] args) {
        getInstance();
    }
}
