package com.yufeng.concurrency.juc.flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. 模拟DOTA 5V5游戏, 需要等待所有人加载完毕, 才能开始游戏
 *      2. 一等多的场景
 * @author yufeng
 * @create 2020-03-29
 */
public class CountDownLatchDemo4 {

    public static void main(String[] args) {
        CountDownLatch players = new CountDownLatch(10);        // 10个玩家
        System.out.println("准备加载");

        for (int i = 0; i < 10; i++) {
            final int no = i + 1;
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep((long)(Math.random() * 10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("No" + no + "玩家加载完毕");
                    players.countDown();
                }
            }).start();
        }

        /** 等待所有玩家加载完毕 */
        try {
            players.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("所有人加载完毕, 准备开干!");
    }
}
