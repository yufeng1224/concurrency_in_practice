package com.yufeng.concurrency.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description
 *      固定线程池使用演示
 * @author yufeng
 * @create 2020-03-10
 */
public class FixedThreadPool01 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Task());
        }

        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println("线程池已经执行完任务, 但是程序不会自动关闭!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /** 执行完上面代码, 线程池还会存活, 需要主动关闭 */
        executorService.shutdown();
    }
}

