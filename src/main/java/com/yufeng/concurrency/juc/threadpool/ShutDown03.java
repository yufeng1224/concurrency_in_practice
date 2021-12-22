package com.yufeng.concurrency.juc.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. 演示关闭线程池相关的方法
 *      2. shutdownNow(): 关闭当前的服务, 并不再接收新的任务, 然后用thread类中的interrupt()尝试中断正在执行的任务, 最后
 *                        返回未处理完的任务列表
 * @author yufeng
 * @create 2020-03-20
 */
public class ShutDown03 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ShutDownTask());
        }

        TimeUnit.SECONDS.sleep(2);

        List<Runnable> runnableList = executorService.shutdownNow();
        System.out.println("未处理的任务数量: " + runnableList.size());

        for (int i = 0; i < runnableList.size(); i++) {
            /** 没有执行的任务用新的线程池执行, 或者记录到日志中以后再执行。。。 进一步的记录 */

        }
    }
}
