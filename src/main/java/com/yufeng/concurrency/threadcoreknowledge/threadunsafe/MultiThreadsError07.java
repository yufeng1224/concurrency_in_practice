package com.yufeng.concurrency.threadcoreknowledge.threadunsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. 第三种线程安全问题: 对象不正确的发布
 *      2. 构造函数中新建线程
 *         2-1 一般情况下, 构造函数中不应该用新开线程的方式完成初始化的工作
 *         2-2 但是在生产中, 有很多实际的应用, 比如在构造函数中获得数据库连接池的引用, 为了加快程序的处理速度,
 *             后台往往都是新开一个线程来处理的。
 *      3. 发布的过早会引起线程安全的问题(比如NPE)
 *      4. 时间不同导致结果不同
 * @author yufeng
 * @create 2020-02-25
 */
public class MultiThreadsError07 {

    private Map<String, String> states;

    public MultiThreadsError07() {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            states = new HashMap<>();
            states.put("1", "周一");
            states.put("2", "周二");
            states.put("3", "周三");
            states.put("4", "周四");
            states.put("5", "周五");
            states.put("6", "周六");
            states.put("7", "周日");
        }).start();
    }

    public Map<String, String> getStates() {
        return states;
    }

    public static void main(String[] args) throws InterruptedException {
        MultiThreadsError07 multiThreadsError07 = new MultiThreadsError07();
        TimeUnit.SECONDS.sleep(1);                                      // 此行不加可能会导致NPE
        System.out.println(multiThreadsError07.getStates().get("1"));
    }
}
