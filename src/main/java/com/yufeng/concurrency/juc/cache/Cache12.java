package com.yufeng.concurrency.juc.cache;

import com.yufeng.concurrency.juc.cache.computable.ExpensiveFunction;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @description
 *      1. 模拟大量请求, 观测缓存效果
 *      2. 利用CountDownLatch实现压测效果
 *      3. 用ThreadLocal确认时间的统一性
 * @author yufeng
 * @create 2020-04-05
 */
public class Cache12 {

    static Cache10<String, Integer> expensiveComputer = new Cache10<>(
            new ExpensiveFunction());

    public static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(100);
//        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            service.submit(() -> {
                Integer result = null;
                try {
                    System.out.println(Thread.currentThread().getName() + "开始等待");
                    /** 压测: 使用await()函数等待。 当100个线程准备完毕, 调用countDown()放行 */
                    countDownLatch.await();

                    /** 用ThreadLocal确认时间的统一性 */
                    SimpleDateFormat dateFormat = ThreadSafeFormatter.dateFormatter.get();
                    String time = dateFormat.format(new Date());
                    System.out.println(Thread.currentThread().getName() + "  " + time + "被放行");

                    result = expensiveComputer.compute("666");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.println(result);
            });
        }

        /** 观测缓存效果 */
//        service.shutdown();
//        while (!service.isTerminated()) {
//
//        }
//        System.out.println("总耗时: " + (System.currentTimeMillis() - start));

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
        service.shutdown();
    }
}


class ThreadSafeFormatter {

    public static ThreadLocal<SimpleDateFormat> dateFormatter = new ThreadLocal<SimpleDateFormat>() {

        // 每个线程会调用本方法一次，用于初始化
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("mm:ss");
        }

        // 首次调用本方法时，会调用initialValue(); 后面的调用会返回第一次创建的值
        @Override
        public SimpleDateFormat get() {
            return super.get();
        }
    };
}