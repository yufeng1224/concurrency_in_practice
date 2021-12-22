package com.yufeng.concurrency.juc.collections.predecessor;

import java.util.Hashtable;

/**
 * @description
 *      1. 演示Hashtable基本的用法, 观看Hashtable源码
 *      2. 线程安全的类
 *      3. 缺点: 存在大量synchronized修饰的方法, 相当于对象锁, 会导致并发性能差
 * @author yufeng
 * @create 2020-03-26
 */
public class HashtableDemo {

    public static void main(String[] args) {
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("key", "123456");
        System.out.println(hashtable.get("key"));
    }

}
