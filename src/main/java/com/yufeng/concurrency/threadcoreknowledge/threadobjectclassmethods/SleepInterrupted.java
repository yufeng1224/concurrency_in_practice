package com.yufeng.concurrency.threadcoreknowledge.threadobjectclassmethods;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @description
 *      每隔1秒钟输出当前时间, 在第6~7秒的时候被中断。
 *      使用TimeUnit类, 更优雅的写法来设置线程等待
 * @author yufeng
 * @create 2020-02-20
 */
public class SleepInterrupted implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SleepInterrupted());
        thread.start();
        Thread.sleep(6500);                           // 第6秒的时候去打断
        thread.interrupt();
    }


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Date());
            try {
                //TimeUnit.HOURS.sleep(3);                  // 3个小时
                //TimeUnit.MINUTES.sleep(25);               // 25分
                TimeUnit.SECONDS.sleep(1);           // 每隔1秒钟
            } catch (InterruptedException e) {
                System.out.println("我被中断了！");
                e.printStackTrace();
            }
        }
    }

}
