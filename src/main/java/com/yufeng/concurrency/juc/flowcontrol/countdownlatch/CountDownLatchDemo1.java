package com.yufeng.concurrency.juc.flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description
 *      1. countDownLatch用法一演示: 一等多应用场景
 *      2. 工厂中质检环节: 5个工人检查, 所有人都认为OK才通过
 * @author yufeng
 * @create 2020-03-29
 */
public class CountDownLatchDemo1 {

    public static void main(String[] args) {
        CountDownLatch checkTimes = new CountDownLatch(5);

        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = () -> {
                try {
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("No." + no + "完成了检查。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    checkTimes.countDown();
                }
            };
            service.submit(runnable);
        }

        System.out.println("等待5个人检查完.....");

        /** 等待所工作线程执行结束, 主线程才可继续执行 */
        try {
            checkTimes.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("所有人都完成了工作, 进入下一个环节");
        service.shutdown();
    }
}
