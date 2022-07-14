package com.yufeng.concurrency.threadcoreknowledge.stopthread.wrongways;


import java.util.concurrent.TimeUnit;

/**
 * @description
 *      案例演示: 不应屏蔽中断
 * @author yufeng
 * @create 2020-02-17
 */
public class WrongWayStopThread implements Runnable{

    @Override
    public void run() {
        while (true && !Thread.currentThread().isInterrupted()) {
            System.out.println("go");
            throwInMethod();
        }
    }

    /**
     *  不推荐在低层方法中抛出异常
     *  这样上层就不会捕获到异常, 也无法检测中断状态!
     */
    private void throwInMethod() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new WrongWayStopThread());
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.start();
    }
}
