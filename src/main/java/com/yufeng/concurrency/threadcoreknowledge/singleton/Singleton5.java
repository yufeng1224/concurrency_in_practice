package com.yufeng.concurrency.threadcoreknowledge.singleton;

/**
 * @description
 *      单例实现方式(五)
 *          1. 懒汉式
 *          2. 非线程安全
 * @author yufeng
 * @create 2020-03-02
 */
public class Singleton5 {

    private static Singleton5 instance;

    private Singleton5() {}

    public static Singleton5 getInstance() {
        if (instance == null) {
            /**
             * 1. 多个线程在'同一时刻'通过外层非空校验, 进入到此处
             * 2. 那么多个线程会依次获得监视器锁, 去执行创建操作
             * 3. 这样还是会产生多个实例
             */
            synchronized (Singleton5.class) {
                instance = new Singleton5();
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        getInstance();
    }
}
