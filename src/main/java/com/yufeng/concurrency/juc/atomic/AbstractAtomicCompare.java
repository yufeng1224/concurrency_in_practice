package com.yufeng.concurrency.juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description
 *      抽象基类
 * @author yufeng
 * @create 2020-03-20
 */
public class AbstractAtomicCompare {

    /**
     * 计算任务的运行耗时
     */
    public static Long calculateTime(Runnable runnable) {
        ExecutorService service = Executors.newFixedThreadPool(40);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            service.submit(runnable);
        }
        service.shutdown();
        while (!service.isTerminated()) {

        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}
