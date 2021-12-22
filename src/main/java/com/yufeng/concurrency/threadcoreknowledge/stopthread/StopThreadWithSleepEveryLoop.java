package com.yufeng.concurrency.threadcoreknowledge.stopthread;

/**
 * @description
 *      1. 目标线程每次迭代都会调用sleep()或wait()等方法进入阻塞状态,
 *      2. 则不需要添加 !Thread.currentThread().isInterrupted()来判断线程是否需要终止
 *      3. 因为只要有一次迭代进入到sleep()方法, 就会抛出异常并结束run()代码
 * @author yufeng
 * @create 2020-02-17
 */
public class StopThreadWithSleepEveryLoop implements Runnable {

    @Override
    public void run() {
        int num = 0;
        /** try-catch是放在while循环外的, 所以能够捕捉到异常并退出 */
        try {
            while (num <= 10000) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num ++;
                Thread.sleep(10);   // 每次循环都阻塞
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread =  new Thread(new StopThreadWithSleepEveryLoop());
        thread.start();

        Thread.sleep(5000);
        thread.interrupt();
    }
}
