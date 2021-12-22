package com.yufeng.concurrency.juc.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description
 *      1. 当需要变成了1000个, 那么必然要用线程池(否则消耗内存太多)
 *      2. 进一步延伸: 1000个打印日期的任务，用线程池来执行
 *      3. 缺点: 这1000个任务, 在date()方法中, 新建了1000个SimpleDateFormat对象
 *      4. 进行优化, 思路: 每个任务都想要用这个对象, SimpleDateFormat对象是否可以只创建一次, 每次都用同一个实例?
 * @author yufeng
 * @create 2020-03-15
 */
public class ThreadLocalNormalUsage03 extends AbstractThreadLocalNormal {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static final int SECONDS = 3600;

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i ++) {
            threadPool.submit(() -> {
                String date = new ThreadLocalNormalUsage03().date(SECONDS);
                System.out.println(date);
            });
        }
        threadPool.shutdown();
    }
}
