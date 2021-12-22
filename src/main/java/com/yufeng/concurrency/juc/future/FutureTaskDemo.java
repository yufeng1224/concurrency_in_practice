package com.yufeng.concurrency.juc.future;

import java.util.concurrent.*;

/**
 * @description
 *      1. 演示FutureTask的用法
 *      2. FutureTask 实现了RunnableFuture接口,
 *         RunnableFuture继承了Runnable和Future接口
 * @author yufeng
 * @create 2020-03-13
 */
public class FutureTaskDemo {

    public static void main(String[] args) {
        Task task = new Task();
        FutureTask<Integer> integerFutureTask = new FutureTask<>(task);
        /** 线程方式 */
//        new Thread(integerFutureTask).start();
        /** 线程池方式 */
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(integerFutureTask);

        try {
            System.out.println("task运行结果: " + integerFutureTask.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}


class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("子线程正在计算");
        TimeUnit.SECONDS.sleep(3);
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum += i;
        }
        return sum;
    }
}

