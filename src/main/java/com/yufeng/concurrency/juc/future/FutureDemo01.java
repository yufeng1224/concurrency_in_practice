package com.yufeng.concurrency.juc.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @description
 *      1. 演示Future接口的使用方法
 *      2. get()方法: 获取结果。
 *         2-1 任务尚未完成(任务还没开始或者还在运行中):  则get()方法将阻塞当前线程, 并直到任务完成
 * @author yufeng
 * @create 2020-03-13
 */
public class FutureDemo01 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Integer> future = service.submit(new CallableTask());
        try {
            /** 会阻塞, 直到计算结果返回 */
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }


    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            TimeUnit.SECONDS.sleep(3);
            return new Random().nextInt(100);
        }
    }

}
