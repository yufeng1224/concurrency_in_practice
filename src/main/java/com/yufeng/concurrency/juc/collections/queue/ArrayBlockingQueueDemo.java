package com.yufeng.concurrency.juc.collections.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. ArrayBlockingQueue使用案例演示
 *      2. 需求: 有10个面试者, 一共只有1个面试官, 大厅里有3个位子供面试者休息, 每个人的面试时间是1秒, 模拟所有人面试的场景
 * @author yufeng
 * @create 2020-03-27
 */
public class ArrayBlockingQueueDemo {

    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);

        Interviewer r1 = new Interviewer(queue);
        Consumer r2 = new Consumer(queue);
        new Thread(r1).start();
        new Thread(r2).start();
    }
}


class Interviewer implements Runnable {

    BlockingQueue<String> queue;

    public Interviewer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("10个候选人都来啦");
        for (int i = 0; i < 10; i++) {
            String candidate = "Candidate" + i;
            try {
                queue.put(candidate);
                System.out.println("安排好了" + candidate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            queue.put("stop");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class Consumer implements Runnable {

    BlockingQueue<String> queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String msg;
        try {
            while(!(msg = queue.take()).equals("stop")){
                System.out.println(msg + "到了");
            }
            System.out.println("所有候选人都结束了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}