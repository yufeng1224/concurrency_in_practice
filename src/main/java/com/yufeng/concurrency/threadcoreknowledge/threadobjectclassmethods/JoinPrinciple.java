package com.yufeng.concurrency.threadcoreknowledge.threadobjectclassmethods;

/**
 * @description
 *     通过讲解join原理, 分析出join的代替写法
 * @author yufeng
 * @create 2020-02-20
 */
public class JoinPrinciple {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完毕");
        });

        thread.start();
        System.out.println("开始等待子线程运行完毕");

        // thread.join();
        synchronized (thread) {         // join 等价代码, 直到隐藏在thread类中notify_all方法唤醒所有线程
            thread.wait();
        }

        System.out.println("所有子线程执行完毕");
    }
}
