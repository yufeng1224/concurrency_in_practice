package com.yufeng.concurrency.threadcoreknowledge.singleton;

/**
 * @description
 *      单例实现方式(七)
 *          1. 静态内部类(推荐)
 *
 * 2020_04_20F: 把内部类的知识点学完, 看内部类终于没有什么压力了!
 *
 * 大的方向是属于懒汉式的, JVM 类加载的性质保证了单例
 *
 * 达到了线程安全和懒加载的两个优点
 * @author yufeng
 * @create 2020-03-02
 */
public class Singleton7 {

    private Singleton7() {}

    private static class SingletonInstance {
        private static final Singleton7 INSTANCE = new Singleton7();
    }

    public static Singleton7 getInstance() {
        return SingletonInstance.INSTANCE;
    }

    public static void main(String[] args) {
        getInstance();
    }
}
