package com.yufeng.concurrency.juc.flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. countDownLatch用法二演示: 多等一应用场景
 *      2. 模拟100米跑步, 8名选手都准备好了, 只等裁判员一声令下, 所有人同时开始跑步
 * @author yufeng
 * @create 2020-03-29
 */
public class CountDownLatchDemo2 {

    public static void main(String[] args) {
        CountDownLatch begin = new CountDownLatch(1);

        ExecutorService service = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 8; i++) {
            final int no = i + 1;
            Runnable runnable = () -> {
                System.out.println("No." + no + "准备完毕, 等待发令枪");
                try {
                    /** 所有运动员进入等待中 */
                    begin.await();
                    System.out.println("No." + no + "开始跑步了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            service.submit(runnable);
        }

        /** 裁判员等待运动员就绪... */
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("发令枪响, 比赛开始！");
        begin.countDown();
        service.shutdown();
    }
}
