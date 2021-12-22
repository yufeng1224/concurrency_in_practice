package com.yufeng.concurrency.juc.flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. 模拟100米跑步, 8名选手都准备好了, 只等裁判员一声令下, 所有人同时开始跑步
 *         当所有人都到终点后, 比赛结束
 *      2. 多等一的场景
 * @author yufeng
 * @create 2020-03-29
 */
public class CountDownLatchDemo3 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch begin = new CountDownLatch(1);                   // 1个裁判
        CountDownLatch athletes = new CountDownLatch(8);                // 8个运动员

        ExecutorService service = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 8; i++) {
            final int no = i + 1;
            Runnable runnable = () ->{
                System.out.println("No." + no + "准备完毕, 等待发令枪");
                try {
                    begin.await();
                    System.out.println("No." + no + "开始跑步了");
                    /** 模拟跑步的时间 */
                    Thread.sleep((long) (Math.random() * 10000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("No." + no + "运动员跑到终点了");
                    athletes.countDown();
                }
            };
            service.submit(runnable);
        }

        /** 裁判员检查发令枪... */
        TimeUnit.SECONDS.sleep(5);
        System.out.println("发令枪响, 比赛开始!");
        begin.countDown();

        /** 等待所有运动员都到达终点 */
        athletes.await();
        System.out.println("所有人到达终点, 比赛结束");

        service.shutdown();
    }
}
