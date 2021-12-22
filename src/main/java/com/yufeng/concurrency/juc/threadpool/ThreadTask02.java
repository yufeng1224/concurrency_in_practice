package com.yufeng.concurrency.juc.threadpool;

/**
 * @description
 *      1. 循环新建1000个线程代码演示
 *      2. 缺点: 线程的生命周期开销较大, 而且使用完给垃圾回收器也带来了很大的压力
 *      3. 总结: 反复创建并销毁线程会占用过多的系统资源, 给程序带来压力
 * @author yufeng
 * @create 2020-03-10
 */
public class ThreadTask02 {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Task());
            thread.start();
        }
    }
}
