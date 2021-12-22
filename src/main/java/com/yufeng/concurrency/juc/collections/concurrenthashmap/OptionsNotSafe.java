package com.yufeng.concurrency.juc.collections.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @description
 *      1. 组合操作并不保证线程安全
 *      2. 原因: 代码中还是存在 a++ 的问题
 *      3. ConcurrentHashMap 能够保证多个线程同时put或者get是线程安全的
 * @author yufeng
 * @create 2020-03-18
 */
public class OptionsNotSafe implements Runnable {

    private static ConcurrentHashMap<String, Integer> scores = new ConcurrentHashMap<String, Integer>();

    /**
     * 代码升级: 保证线程安全
     */
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            while (true) {
                Integer score = scores.get("小明");
                Integer newScore = score + 1;
                /** 使用replace()函数替代put() */
                boolean b = scores.replace("小明", score, newScore);
                if (b) {
                    break;
                }
            }
        }
    }


    /**
     * 线程非安全
     */
//    @Override
//    public void run() {
//        for (int i = 0; i < 1000; i++) {
//            Integer score = scores.get("小明");
//            /** 该行代码不是线程安全的 */
//            Integer newScore = score + 1;
//            scores.put("小明", newScore);
//        }
//    }


    public static void main(String[] args) throws InterruptedException {
        scores.put("小明", 0);
        Thread t1 = new Thread(new OptionsNotSafe());
        Thread t2 = new Thread(new OptionsNotSafe());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(scores);
    }
}
