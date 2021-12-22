package com.yufeng.concurrency.threadcoreknowledge.property;


/**
 * @description
 *      线程的Id属性演示
 *      Id从1开始，JVM运行起来后，会启动多个守护线程, 所以开发者创建的线程的ID早已不是1
 *      Id属性的特点
 *         1. 数据类型是long
 *         2. 线程Id是唯一的, 并且在生命周期里是保持不变的
 *         3. 线程终止后, 线程的Id可以被重复使用
 * @author yufeng
 * @create 2020-02-23
 */
public class ThreadId {

    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("主线程的ID" + Thread.currentThread().getId());  // 1
        System.out.println("子线程的ID" + thread.getId());                  // 9 数值不一定
    }
}
