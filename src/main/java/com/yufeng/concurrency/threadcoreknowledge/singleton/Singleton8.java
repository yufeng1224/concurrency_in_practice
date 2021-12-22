package com.yufeng.concurrency.threadcoreknowledge.singleton;

/**
 * @description
 *      枚举单例[推荐用]
 *      生产实践中最佳的单例的写法
 *      需要去搜索一下为什么!
 * @author yufeng
 * @create 2020-03-02
 */
public enum Singleton8 {

    INSTANCE;

    public void whatever() {
        // 方法...
    }

    public static void main(String[] args) {
        Singleton8.INSTANCE.whatever();
    }

}
