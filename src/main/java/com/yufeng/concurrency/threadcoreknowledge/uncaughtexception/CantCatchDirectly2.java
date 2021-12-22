package com.yufeng.concurrency.threadcoreknowledge.uncaughtexception;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. 子线程出现未捕获的异常
 *      2. 主线程添加try-catch, 期望捕获到第1个线程的异常之后, 线程2,3,4不执行
 *      3. 结果: 执行时发现主线程根本没有捕获到异常。失效原因: try-catch是针对主线程的, 没有办法捕获到子线程中的异常
 *      4. 结论: 线程的异常是不能用传统的方法进行捕获
 * @author yufeng
 * @create 2020-02-23
 */
public class CantCatchDirectly2 implements Runnable {

    public static void main(String[] args) {
        try {
            new Thread(new CantCatchDirectly(), "MyThread-1").start();
            TimeUnit.SECONDS.sleep(2);

            new Thread(new CantCatchDirectly(), "MyThread-2").start();
            TimeUnit.SECONDS.sleep(2);

            new Thread(new CantCatchDirectly(), "MyThread-3").start();
            TimeUnit.SECONDS.sleep(2);

            new Thread(new CantCatchDirectly(), "MyThread-4").start();
        } catch (Exception e) {
            System.out.println("主线程尝试捕获子线程抛出的异常");      // 无法捕捉到子线程异常, 当前语句不执行
        }
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程正在执行");
        int i = 1/0;
        System.out.println(Thread.currentThread().getName() + "线程是否继续执行");
        System.out.println(i);
    }
}
