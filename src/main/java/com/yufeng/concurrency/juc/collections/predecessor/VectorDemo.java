package com.yufeng.concurrency.juc.collections.predecessor;

import java.util.Vector;

/**
 * @description
 *      1. 演示Vector基本用法, 观看Vector源码
 *      2. 底层基于动态数组实现, 是线程安全的类
 *      3. 缺点: 并发性能差, 因为方法中使用到了synchronized关键字, 相当于对象锁。
 *         比如线程1操作Vector类中的size()方法时, 其他线程只能等待, 无法进行其它操作
 * @author yufeng
 * @create 2020-03-26
 */
public class VectorDemo {

    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        vector.add("test");
        System.out.println(vector.get(0));
    }
}
