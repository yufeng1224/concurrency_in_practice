package com.yufeng.concurrency.juc.threadpool;

import java.util.concurrent.*;

/**
 * @description
 *      手工创建线程池
 * @author yufeng
 * @create 2020-03-10
 */
public class ManualCreateThreadPool {

    public static void main(String[] args)  {

        // 拒绝策略可以不传, 构造函数内部会调用; 线程工厂也可以不传, 2个都是默认的
        ExecutorService executorService = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10));

        // 输入15没问题, 输入16就会报错!
        for (int i = 0; i < 15; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ": 打印");
                }
            });
        }

        executorService.shutdown();


    }

}
