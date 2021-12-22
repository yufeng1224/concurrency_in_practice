package com.yufeng.concurrency.juc.future;

import java.util.concurrent.*;

/**
 * @description
 *      1. 正在运行中的callable任务尝试中断
 *      2. 设置cancel(true), 代码不会继续执行了, 布尔值返回为true
 *      3. 设置cancel(false), 代码还会继续执行, 布尔值返回为true
 * @author yufeng
 * @create 2021-12-01
 */
public class FutureDemo08 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> future = service.submit(new CallableTask());

        try {
            /** 主线程等待3秒, 开始尝试中断 */
            TimeUnit.SECONDS.sleep(3);

            /** 设置cancel(true) */
//            boolean cancelled = future.cancel(true);
//            System.out.println("任务是否被中断了: " + cancelled);     // true

            /** 设置cancel(false)*/
            boolean cancelled = future.cancel(false);
            System.out.println("任务是否被中断了,: " + cancelled);      // true

        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("InterruptedException异常");
        }

        service.shutdown();
    }


    static class CallableTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("任务开始执行, 设置等待10秒");
            TimeUnit.SECONDS.sleep(10);
            System.out.println("代码继续执行");       // 设置 cancel(true), 代码则不会继续执行了
            return 0;
        }
    }
}
