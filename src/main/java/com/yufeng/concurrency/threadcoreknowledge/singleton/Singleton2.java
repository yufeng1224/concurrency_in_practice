package com.yufeng.concurrency.threadcoreknowledge.singleton;

/**
 * @description
 *      单例模式实现方式(二):
 *          1. 饿汉式 ———— 静态代码块写法
 *          2. 类加载的时候就完成了实例化, 由JVM保证线程安全
 * @author yufeng
 * @create 2020-03-02
 */
public class Singleton2 {

    private final static Singleton2 INSTANCE;

    static {
        INSTANCE = new Singleton2();
    }

    private Singleton2() {}

    public static Singleton2 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        getInstance();
    }

}
