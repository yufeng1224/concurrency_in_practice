package com.yufeng.concurrency.threadcoreknowledge.stopthread;

/**
 * @description
 *      生产最佳实践(恢复中断方式)
 *         1. 底层方法中使用try-catch捕捉到异常之后;
 *         2. 在catch语句块中调用Thread.currentThread().interrupt()来恢复设置中断状态,
 *            以便于在后续的执行中, 依然能够检查刚才发生了中断
 * @author yufeng
 * @create 2020-02-17
 */
public class RightWayStopThreadInProd2 implements Runnable {

    @Override
    public void run() {
        while (true) {
            /** reInterrupt()方法恢复中断， 所以这里能够检查刚才发生了中断 */
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted, 程序运行结束");
                break;
            }
            reInterrupt();
        }
    }


    /**
     * catch语句块中恢复中断状态
     */
    private void reInterrupt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException{
        Thread thread = new Thread(new RightWayStopThreadInProd2());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
