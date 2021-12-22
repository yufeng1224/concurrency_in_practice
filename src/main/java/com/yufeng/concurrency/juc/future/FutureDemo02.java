package com.yufeng.concurrency.juc.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @description
 *      1. 演示Future接口的方法, 用lambda形式表示
 *      2. get()方法: 获取结果
 *         2-1 任务执行过程中抛出了异常。不论call()执行是抛出的异常类型是什么, 最后上层函数捕捉到的异常类型都是ExecutionException
 * @author yufeng
 * @create 2020-03-13
 */
public class FutureDemo02 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Callable<Integer> callable = () -> {
            int i = 1 / 0;
            System.out.println(i);
            return new Random().nextInt(200);
        };

        Future<Integer> future = service.submit(callable);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("执行过程遇到了异常, 抛出的异常统一为: ExecutionException");
            e.printStackTrace();
        }
        service.shutdown();
    }

}
