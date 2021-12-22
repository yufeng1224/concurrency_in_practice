package com.yufeng.concurrency.juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

/**
 * @description
 *      1. 演示高并发场景下，LongAdder比AtomicLong性能好
 * @author yufeng
 * @create 2020-03-20
 */
public class LongAdderDemo extends AbstractAtomicCompare {

    public static void main(String[] args) {
        LongAdder counter = new LongAdder();
        Runnable runnable = new Task(counter);

        Long time = calculateTime(runnable);
        System.out.println(counter.sum());
        System.out.println("LongAdder耗时：" + time);
    }


    private static class Task implements Runnable {

        private LongAdder counter;

        public Task(LongAdder counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        }
    }
}
