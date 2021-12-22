package com.yufeng.concurrency.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description
 *      可缓存线程池代码演示
 * @author yufeng
 * @create 2020-03-10
 */
public class CachedThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i ++) {
            executorService.execute(new Task());
        }
        executorService.shutdown();
    }
}
