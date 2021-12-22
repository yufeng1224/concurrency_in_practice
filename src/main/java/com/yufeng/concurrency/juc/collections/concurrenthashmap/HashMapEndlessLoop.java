package com.yufeng.concurrency.juc.collections.concurrenthashmap;

import java.util.HashMap;

/**
 * @description
 *      1. 演示HashMap在多线程情况下造成死循环的情况(需JDK7版本)
 *      2. CPU直接100%
 *      3. 原因: 多个线程同时扩容, 可能会造成循环链表, 导致死循环
 * @author yufeng
 * @create 2020-03-26
 */
public class HashMapEndlessLoop {

    private static HashMap<Integer, String> map = new HashMap<>(2, 1.5f);

    public static void main(String[] args) {
        map.put(5, "C");
        map.put(7, "B");
        map.put(3, "A");

        new Thread(new Runnable() {
            @Override
            public void run() {
                map.put(15, "D");
                System.out.println(map);
            }
        }, "Thread-1").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                map.put(1, "E");
                System.out.println(map);
            }
        }, "Thread-2").start();
    }
}





























