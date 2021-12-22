package com.yufeng.concurrency.threadcoreknowledge.synchronize.base;

/**
 * @descrption
 *      公共类
 * @author yufeng
 * @create 2020-02-21
 */
public class BaseMain {

    public static void executeShow(Runnable... instanceArgs) {
        Thread t1 = new Thread(instanceArgs[0], "t1");
        Thread t2 = new Thread(instanceArgs[instanceArgs.length - 1], "t2");

        t1.start();
        t2.start();

        while (t1.isAlive() || t2.isAlive()) {          // 等待t1和t2线程执行完毕
        }

        System.out.println("finished");
    }
}
