package com.yufeng.concurrency.juc.flowcontrol.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @description
 *      演示CyclicBarrier的基本用法
 * @author yufeng
 * @create 2020-03-30
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,
                ()-> System.out.println("所有人都到场了, 大家统一出发！"));

//        for (int i = 0; i < 5; i++) {
//            new Thread(new Task(i, cyclicBarrier)).start();
//        }

        /** CyclicBarrier可以重用, 10个线程那就分两批出发 */
        for (int i = 0; i < 10; i++) {
            new Thread(new Task(i, cyclicBarrier)).start();
        }
    }


    static class Task implements Runnable{

        private int id;

        private CyclicBarrier cyclicBarrier;

        public Task(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + id + "现在前往集合地点");
            try {
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println("线程" + id + "到了集合地点, 开始等待其他人到达");
                cyclicBarrier.await();
                System.out.println("线程" + id + "出发了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
