package com.yufeng.concurrency.juc.future;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @description
 *      1. 演示Future接口的方法
 *      2. 当在执行callable任务时, 使用了cancel()函数, 后面的get()调用会抛出CancellationException
 *         第一次调用cancel()会返回true, 第二次调用返回false
 * @author yufeng
 * @create 2020-03-13
 */
public class FutureDemo07 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        ArrayList<Future> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Integer> future = service.submit(new FutureDemo06.CallableTask());
            futures.add(future);
            if (i == 3) {
                boolean cancelled =  future.cancel(true);
                System.out.println("第一次被取消, 返回的布尔值: " + cancelled);
            }
        }

        for (int i = 0; i < 10; i++) {
            Future<Integer> future = futures.get(i);
            try {
                /** 由于调用了cancel()函数, 抛出了CancellationException */
                Integer integer = future.get();
                System.out.println("结果输出:" + integer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (CancellationException e) {
                System.out.println("任务已经被取消了");
            }
        }

        /** 查看指定任务是否已经被取消*/
        boolean cancelled = futures.get(3).cancel(true);
        System.out.println("查看指定任务被取消后, 再次调用cancel()方法返回的布尔值: " + cancelled);

        service.shutdown();
    }
}
