package com.yufeng.concurrency.juc.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description
 *      定时任务线程池代码演示
 * @author yufeng
 * @create 2020-03-10
 */
public class ScheduledThreadPool {

    public static void main(String[] args) {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);
        /** 5秒后执行该任务 */
        threadPool.schedule(new Task(), 5, TimeUnit.SECONDS);

        /** 1秒后开始执行任务, 并且每隔3秒周期地打印任务 */
        threadPool.scheduleAtFixedRate(new Task(), 1, 3, TimeUnit.SECONDS);

        threadPool.shutdown();
    }
}
