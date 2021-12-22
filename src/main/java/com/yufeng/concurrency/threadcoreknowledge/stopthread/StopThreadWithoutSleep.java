package com.yufeng.concurrency.threadcoreknowledge.stopthread;

/**
 * @description
 *      普通情况演示(一)
 *         1. 当run()方法内没有sleep()或者wait()方法时;
 *         2. 使用thread.interrupt()方法尝试打断目标线程;
 *         3. 结论: 程序还是正常执行结束, 说明interrupt()方法打断目标线程是一种合作机制。
 *            正在运行的目标线程会根据自己的业务来判断是否终止;
 * @author yufeng
 * @create 2020-02-17
 */
public class StopThreadWithoutSleep implements Runnable {

    @Override
    public void run() {
        int num = 0;
        while (num < Integer.MAX_VALUE / 2) {
            if (num % 1000 == 0) {
                System.out.println(num + "是1000的倍数");
            }
            num ++;
        }
        System.out.println("任务运行结束了");
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new StopThreadWithoutSleep());
        thread.start();
        Thread.sleep(1000);

        /** 尝试打断目标线程 */
        thread.interrupt();
    }
}
