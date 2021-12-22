package com.yufeng.concurrency.threadcoreknowledge.stopthread.wrongways;

/**
 * @description
 *      停止线程的错误方式:
 *         1. 用 stop()来停止线程, 会导致线程运行一半突然停止，没办法完成一个基本单位的操作, 造成脏数据;
 *         2. 比如银行的转账操作, 本来要求有10笔交易是必须一起成功的，结果交易到第8笔的时候，线程突然停止了，
 *            这会导致很严重的数据问题;
 *         3. 总的来说,  stop()会破坏数据的原子性, 造成非常严重的后果;
 * @author yufeng
 * @create 2020-02-17
 */
public class StopThread implements Runnable {

    @Override
    public void run() {
        /** 模拟指挥军队：一共有5个连队，每个连队10人，以连队为单位，发放武器弹药，叫到号的士兵前去领取 */
        for (int i = 0; i < 5; i ++) {
            System.out.println("连队" + i + "开始领取武器");
            for (int j = 0; j < 10; j ++) {
                System.out.println(j);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("连队" + i + "已经领取完毕");
        }
    }


    public static void main(String[] args) {
        Thread thread = new Thread(new StopThread());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop();
    }
}
