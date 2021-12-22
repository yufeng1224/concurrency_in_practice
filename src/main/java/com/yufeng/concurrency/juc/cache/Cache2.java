package com.yufeng.concurrency.juc.cache;

import com.yufeng.concurrency.juc.cache.computable.Computable;
import com.yufeng.concurrency.juc.cache.computable.ExpensiveFunction;
import java.util.HashMap;
import java.util.Map;

/**
 * @description
 *      1. 用装饰者模式解耦, 给计算器自动添加缓存功能
 *      2. 解决代码复用能力差的问题(缓存功与计算功能解耦)
 * @author yufeng
 * @create 2020-04-05
 */
public class Cache2<A,V> implements Computable<A,V> {

    private final Map<A, V> cache = new HashMap();

    private final Computable<A,V> c;

    /** 初始化传入计算的具体实现类 */
    public Cache2(Computable<A,V> c) {
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


    public static void main(String[] args) throws Exception {
        Cache2<String, Integer> expensiveComputer = new Cache2<>(
                new ExpensiveFunction());
        Integer result = expensiveComputer.compute("666");
        System.out.println("第一次计算结果: " + result);

        result = expensiveComputer.compute("666");
        System.out.println("第二次计算结果: " + result);
    }
}
