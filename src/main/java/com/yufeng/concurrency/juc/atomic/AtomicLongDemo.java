package com.yufeng.concurrency.juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @description
 *      1. 演示高并发场景下，LongAdder比AtomicLong性能好
 * @author yufeng
 * @create 2020-03-20
 */
public class AtomicLongDemo extends AbstractAtomicCompare {

    public static void main(String[] args) {
        AtomicLong counter = new AtomicLong(0);
        Runnable runnable = new Task(counter);

        Long time = calculateTime(runnable);
        System.out.println(counter.get());
        System.out.println("AtomicLong耗时：" + time);
    }


    private static class Task implements Runnable {

        private AtomicLong counter;

        public Task(AtomicLong counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.incrementAndGet();
            }
        }
    }
}
