package com.yufeng.concurrency.threadcoreknowledge.singleton;

/**
 * @description
 *      单例实现方式(三)
 *          1. 懒汉式
 *          2. 此种实现方式线程不安全
 * @author yufeng
 * @create 2020-03-02
 */
public class Singleton3 {

    private static Singleton3 instance;

    private Singleton3() {}

    public static Singleton3 getInstance() {
        /** 多线程同时执行到这行代码, 判断null, 结果都会进入到方法体内并且执行new操作 */
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }

    public static void main(String[] args) {
        getInstance();
    }
}
