package com.yufeng.concurrency.juc.cache;

import com.yufeng.concurrency.juc.cache.computable.Computable;
import com.yufeng.concurrency.juc.cache.computable.ExpensiveFunction;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description
 *      1. 重复计算代码演示
 *      2. 解决方案: Future和Callable组合
 * @author yufeng
 * @create 2020-04-05
 */
public class Cache6<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public Cache6(Computable<A, V> c) {
        this.c = c;
    }


    @Override
    public V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }


    public static void main(String[] args) {
        Cache6<String, Integer> expensiveComputer = new Cache6<>(
                new ExpensiveFunction());
        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("777");
                System.out.println("第一次的计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("778");
                System.out.println("第二次的计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("777");
                System.out.println("第三次的计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
