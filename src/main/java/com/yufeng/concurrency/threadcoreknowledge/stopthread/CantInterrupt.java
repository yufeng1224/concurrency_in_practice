package com.yufeng.concurrency.threadcoreknowledge.stopthread;

/**
 * @description
 *     1. while内放置try/catch代码块, 导致中断失效的问题
 *     2. 原因: 当线程一旦响应中断, interrupt标记位就会被清除, 然后重新进入while循环时，
 *        Thread.currentThread().isInterrupted变为了false， 所以能够继续执行
 *     3. 解决方案: 在catch中重新恢复中断
 * @author yufeng
 * @create 2020-02-17
 */
public class CantInterrupt implements Runnable {

    @Override
    public void run() {
        int num = 0;
        while (num <= 10000 && !Thread.currentThread().isInterrupted()) {
            if (num % 100 == 0) {
                System.out.println(num + "是100的整数");
            }
            num ++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                //Thread.currentThread().interrupt();             // 第一种解决方式: 恢复中断
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new CantInterrupt());
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
