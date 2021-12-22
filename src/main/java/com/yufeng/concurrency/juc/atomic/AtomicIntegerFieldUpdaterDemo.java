package com.yufeng.concurrency.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @description
 *      1. 演示AtomicIntegerFieldUpdater的用法
 *      2. AtomicIntegerFieldUpdater作用: 可以对普通变量进行升级
 * @author yufeng
 * @create 2020-03-20
 */
public class AtomicIntegerFieldUpdaterDemo implements Runnable{

    static Candidate tom;
    static Candidate peter;

    /** 设置Candidate对象和需要升级的变量名score */
    public static AtomicIntegerFieldUpdater<Candidate> scoreUpdater = AtomicIntegerFieldUpdater
            .newUpdater(Candidate.class, "score");


    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            peter.score ++;
            /** 升级tom对象中的score变量进行自加操作 */
            scoreUpdater.getAndIncrement(tom);
        }
    }


    public static class Candidate {
        /**
         * 注意点:
         *    1. score变量必须带volatile关键字, 否则会抛出 java.lang.IllegalArgumentException: Must be volatile type
         *    2. 变量不支持static
         */
        volatile int score;         // 变量名
    }


    public static void main(String[] args) throws InterruptedException {
        tom = new Candidate();
        peter = new Candidate();

        AtomicIntegerFieldUpdaterDemo r = new AtomicIntegerFieldUpdaterDemo();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("普通变量：" + peter.score);
        System.out.println("升级后的结果: " + tom.score);
    }
}
