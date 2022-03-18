package com.yufeng.concurrency.threadcoreknowledge.createthread.wrongways;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @description
 *      1. 类FutureTask实现了RunnableFuture接口;
 *      2. RunnableFuture接口继承了Runnable接口、Future接口;
 *      3. 所以本质上还是通过实现Runnable接口的方式创建线程;
 * @author yufeng
 * @create 2020-02-14
 */
public class CallableDemo implements Callable<Integer> {

    @Override
    public Integer call() {
        int sum = 1;
        for (int i = 0; i < 10; i++) {
            sum += i;
        }
        System.out.println("当前线程是: " + Thread.currentThread().getName());
        return sum;
    }

    public static void main(String[] args) {
        CallableDemo call = new CallableDemo();
        FutureTask<Integer> futureTask = new FutureTask<>(call);
        Thread thread = new Thread(futureTask, "thread-callable");
        thread.start();

        try {
            int num = futureTask.get();
            System.out.println("返回值是: " + num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
