package com.yufeng.concurrency.juc.cache;

import com.yufeng.concurrency.juc.cache.computable.Computable;
import com.yufeng.concurrency.juc.cache.computable.ExpensiveFunction;
import java.util.HashMap;
import java.util.Map;

/**
 * @description
 *      1. 演示并发情况下的性能问题
 *      2. 当多个线程同时计算的时候, 需要慢慢等待, 严重时性能比不用缓存更差
 * @author yufeng
 * @create 2020-04-05
 */
public class Cache3<A,V> implements Computable<A,V> {

    private final Map<A, V> cache = new HashMap();

    private  final Computable<A,V> c;

    public Cache3(Computable<A, V> c) {
        this.c = c;
    }


    @Override
    public synchronized V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }


    public static void main(String[] args) {
        Cache3<String, Integer> expensiveComputer = new Cache3<>(
                new ExpensiveFunction());
        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("666");
                System.out.println("第一次的计算结果："+result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("667");
                System.out.println("第二次的计算结果："+result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        /** 无法利用到缓存 */
        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("666");
                System.out.println("第三次的计算结果："+result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
