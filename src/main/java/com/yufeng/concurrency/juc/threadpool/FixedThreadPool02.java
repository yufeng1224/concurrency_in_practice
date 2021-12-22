package com.yufeng.concurrency.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. 演示固定线程池OOM的情况
 *      2. 设置下内存 -Xmx8m -Xms8m
 *      3. 原因: 无界队列中塞入过多任务, 导致内存溢出
 * @author yufeng
 * @create 2020-03-10
 */
public class FixedThreadPool02 {

    private static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(new SubThread());
        }
    }
}


class SubThread implements Runnable {
    @Override
    public void run() {
        try {
            /** 让当前线程一直处于运行中, 后面的任务则能够一直加入到队列中 */
            TimeUnit.SECONDS.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
