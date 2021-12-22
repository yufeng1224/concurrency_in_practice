package com.yufeng.concurrency.juc.cache;

import com.yufeng.concurrency.juc.cache.computable.Computable;
import com.yufeng.concurrency.juc.cache.computable.ExpensiveFunction;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @description
 *      1. 利用Future, 避免重复计算。 将compute()计算任务放到callable中
 *      2. 缓存中需要解决的一个重要问题
 *      3. 当前类依然存在重复的可能: 两个线程同时调用cache.get方法, 返回的结果都为null, 后面还是会
 *         创建两个任务去计算相同的值。
 *         (解决方案: 用原子操作putIfAbsent)
 * @author yufeng
 * @create 2020-04-05
 */
public class Cache7<A, V> implements Computable<A, V> {

    /** value变成Future类型, 来避免重复计算 */
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public Cache7(Computable<A, V> c) {
        this.c = c;
    }


    @Override
    public V compute(A arg) throws Exception {
        Future<V> f = cache.get(arg);
        if (f == null) {
            /** 具体任务 */
            Callable<V> callable = () -> c.compute(arg);
            FutureTask<V> ft = new FutureTask<>(callable);
            f = ft;
            /**
             * 1. 在真正计算之前, 先将FutureTask放入到Map中!
             * 2. 利用ConcurrentHashMap的可见性保证(不会放入两次)
             */
            cache.put(arg, ft);

            System.out.println("从FutureTask调用了计算函数");
            ft.run();
        }
        /** 如果计算没有结束, 当前线程会阻塞。等待其他线程计算返回的结果 */
        return f.get();
    }


    public static void main(String[] args) {
        Cache7<String, Integer> expensiveComputer = new Cache7<>(
                new ExpensiveFunction());

        /** 计算666的两个线程 相继先后执行, 还是会存在问题! */
        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("666");
                System.out.println("第一次计算的结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("666");
                System.out.println("第三次计算的结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("667");
                System.out.println("第二次的计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }
}
