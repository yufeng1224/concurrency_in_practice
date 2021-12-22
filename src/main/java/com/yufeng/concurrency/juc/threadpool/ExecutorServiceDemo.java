package com.yufeng.concurrency.juc.threadpool;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description
 *      ExecutorService接口用法演示
 * @author yufeng
 * @create 2020-03-10
 */
public class ExecutorServiceDemo {

    /**
     * 使用Executors工厂方法来创建ExecutorService
     */
    public static ExecutorService executorService1 = Executors.newSingleThreadExecutor();
    public static ExecutorService executorService2 = Executors.newFixedThreadPool(10);
    public static ExecutorService executorService3 = Executors.newCachedThreadPool();


    public static void main(String[] args) throws Exception {
        /** 使用方法演示 */
        // 1. 执行Runnable任务对象
        executorService1.execute(() -> System.out.println("Asynchronous task"));
        // 2. 关闭线程池
        executorService1.shutdown();

        // 3. 提交Callable任务对象, 用future对象进行接收
        Future future = executorService2.submit(() -> "Callable Result");
        System.out.println("future result: " + future.get());
        executorService2.shutdown();

        /** 创建Callable任务集合 */
        Set<Callable<String>> callables = new HashSet<>();
        callables.add(() -> "Callable Task1");
        callables.add(() -> "Callable Task2");
        callables.add(() -> "Callable Task3");
        callables.add(() -> "Callable Task4");

        // 4. 执行某一个Callable任务对象
        String callableResult = executorService3.invokeAny(callables);
        System.out.println("callable result: " + callableResult);

        // 5. 执行集合中的所有Callable任务对象, 并且返回Future对象集合
        List<Future<String>> futureList = executorService3.invokeAll(callables);
        for (int i = 0; i < futureList.size(); i++) {
            Future futureResult = futureList.get(i);
            System.out.println("invoke all result: " + futureResult.get());
        }
        executorService3.shutdown();
    }
}
