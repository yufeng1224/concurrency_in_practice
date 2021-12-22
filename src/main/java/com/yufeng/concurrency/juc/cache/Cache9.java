package com.yufeng.concurrency.juc.cache;

import com.yufeng.concurrency.juc.cache.computable.Computable;
import com.yufeng.concurrency.juc.cache.computable.MayFail;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @description
 *      1. 计算抛出异常的情况代码演示
 *      2. 注意"缓存污染"问题
 * @author yufeng
 * @create 2020-04-05
 */
public class Cache9<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public Cache9(Computable<A, V> c) {
        this.c = c;
    }


    @Override
    public V compute(A arg) throws InterruptedException, ExecutionException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> callable = () -> c.compute(arg);
                FutureTask<V> ft = new FutureTask<>(callable);
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    System.out.println("从FutureTask调用计算函数");
                    ft.run();
                }
            }

            try {
                /** get()可能出现异常, 对异常进行处理 */
                return f.get();
            } catch (CancellationException e) {
                System.out.println("被取消了");
                /** 不清理可能会造成缓存污染! */
                cache.remove(arg);
                throw e;
            } catch (InterruptedException e) {
                cache.remove(arg);
                throw e;
            } catch (ExecutionException e) {
                /** 计算结果出错则进行重试 */
                System.out.println("计算错误, 需要重试");
                cache.remove(arg);
            }
        }
    }


    public static void main(String[] args) throws Exception {
        Cache9<String, Integer> expensiveComputer = new Cache9<>(
                new MayFail());

        new Thread(()-> {
            try {
                Integer result = expensiveComputer.compute("555");
                System.out.println("第一次的计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("555");
                System.out.println("第三次的计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("888");
                System.out.println("第二次的计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        /** 取消任务演示 */
//        Future<Integer> integerFuture = expensiveComputer.cache.get("666");
//        integerFuture.cancel(true);

    }
}
