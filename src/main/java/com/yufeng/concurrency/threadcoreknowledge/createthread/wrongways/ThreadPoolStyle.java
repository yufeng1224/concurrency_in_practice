package com.yufeng.concurrency.threadcoreknowledge.createthread.wrongways;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description
 *      1. 错误观点: 线程池创建线程也是一种新建线程的方式
 *      2. 错误原因解析
 *           2-1 当调用 newCachedThreadPool()的时候, 会创建一个静态内部类 DefaultThreadFactory
 *           2-2 在执行 submit()方法的时候, 它会将task实例参数传入
 *           2-3 在submit()源码内部, 有一个 new Worker() 的操作, 它会调用 DefaultThreadFactory的
 *               new Thread()方法来创建新线程
 *           2-4 所以说, 线程池本质上还是通过实现 Runnable 接口的方式来创建线程的, 只不过代码的外部做了
 *               很多包装
 * @author yufeng
 * @create 2020-02-14
 */
public class ThreadPoolStyle {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Task());
        }
    }
}


class Task implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
