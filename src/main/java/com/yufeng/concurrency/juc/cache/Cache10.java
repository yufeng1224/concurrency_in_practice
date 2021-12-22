package com.yufeng.concurrency.juc.cache;

import com.yufeng.concurrency.juc.cache.computable.Computable;
import com.yufeng.concurrency.juc.cache.computable.MayFail;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description
 *      出于安全性考虑，缓存需要设置有效期，到期自动失效，否则如果缓存一直不失效，那么带来缓存不一致等问题
 * @author yufeng
 * @create 2020-04-05
 */
public class Cache10<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public Cache10(Computable<A, V> c) {
        this.c = c;
    }


    @Override
    public V compute(A arg) throws InterruptedException, ExecutionException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> callable = () ->  c.compute(arg);
                FutureTask<V> ft = new FutureTask<>(callable);
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    System.out.println("从FutureTask中调用计算函数");
                    ft.run();
                }
            }

            try {
                return f.get();
            } catch (CancellationException e) {
                System.out.println("任务被取消了");
                cache.remove(arg);
                throw e;
            } catch (InterruptedException e) {
                cache.remove(arg);
                throw e;
            } catch (ExecutionException e) {
                System.out.println("计算错误, 需要重试");
                cache.remove(arg);
            }
        }
    }


    /**
     * 生成一个随机时间值
     */
    public V computeRandomExpire(A arg) throws ExecutionException, InterruptedException {
        long randomExpire = (long) (Math.random() * 10000);
        return compute(arg, randomExpire);
    }


    /** 定时任务线程池 */
    public final static ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);


    public V compute(A arg, long expire) throws ExecutionException, InterruptedException {
        if (expire > 0) {
            executor.schedule(new Runnable() {
                @Override
                public void run() {
                    /** 时间到了以后做清除工作 */
                    expire(arg);
                }
            }, expire, TimeUnit.MILLISECONDS);
        }
        return compute(arg);
    }


    /**
     *
     */
    public synchronized void expire(A key) {
        Future<V> future = cache.get(key);
        if (future != null) {
            if (!future.isDone()) {
                System.out.println("Future任务被取消");
                future.cancel(true);
            }
            System.out.println("过期时间到, 缓存被清除");
            cache.remove(key);
        }
    }


    public static void main(String[] args) throws Exception {
        Cache10<String, Integer> expensiveComputer = new Cache10<>(
                new MayFail());

        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("666",5000L);
                System.out.println("第一次的计算结果: " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("666");
                System.out.println("第三次的计算结果: " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("667");
                System.out.println("第二次的计算结果: " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        /** 演示一下过期的情况, 先休眠6秒 */
        TimeUnit.SECONDS.sleep(6);
        Integer result = expensiveComputer.compute("666");
        System.out.println("主线程的计算结果: " + result);
    }
}
