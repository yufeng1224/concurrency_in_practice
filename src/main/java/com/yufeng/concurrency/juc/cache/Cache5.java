package com.yufeng.concurrency.juc.cache;

import com.yufeng.concurrency.juc.cache.computable.Computable;
import com.yufeng.concurrency.juc.cache.computable.ExpensiveFunction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description
 *     1. 使用ConcurrentHashMap解决并发问题, 保证并发安全
 *     2. 当前类还存在的缺点: 在计算完成前, 另一个要求计算相同值的请求到来, 会导致计算两遍,
 *        这和缓存向避免多次计算的初衷恰恰相反, 是不可能接受的
 *     3. 重复的计算
 * @author yufeng
 * @create 2020-04-05
 */
public class Cache5<A, V> implements Computable<A, V> {

    /** 将HashMap替换为ConcurrentHashMap, 保证并发安全 */
    private final Map<A, V> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public Cache5(Computable<A, V> c) {
        this.c = c;
    }


    @Override
    public V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        V result = cache.get(arg);
        /**
         * 1. 两个线程可能会同时或者先后进入计算相同的数据
         * 2. 因为计算过程可能是比较耗时的, 先进入的线程可能还没有计算完结果
         */
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }


    public static void main(String[] args) throws Exception {
        Cache5<String, Integer> expensiveComputer = new Cache5<>(
                new ExpensiveFunction());
        Integer result = expensiveComputer.compute("666");
        System.out.println("第一次计算结果: " + result);

        result = expensiveComputer.compute("666");
        System.out.println("第二次计算结果: " + result);
    }
}
