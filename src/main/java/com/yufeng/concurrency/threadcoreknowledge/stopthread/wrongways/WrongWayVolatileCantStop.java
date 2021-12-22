package com.yufeng.concurrency.threadcoreknowledge.stopthread.wrongways;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. 演示用volatile的局限: 陷入阻塞时, volatile是无法停止线程的
 *      2. 此例中，生产者的生成速度很快，消费者消费速度慢, 所以阻塞队列慢满以后，生产者会阻塞,
 *         等待消费者进一步消费
 * @author yufeng
 * @create 2020-02-17
 */
public class WrongWayVolatileCantStop {

    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);

        /** 生产者线程创建, 并开始生产 */
        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        TimeUnit.SECONDS.sleep(1);

        /** 消费者 */
        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了。");

        /**  一旦消费不需要更多数据了，我们应该让生产者也停下来，但是实际情况是程序并没有停止 */
        producer.canceled = true;
        System.out.println(producer.canceled);          // true

    }
}


/**
 * 生产者线程
 */
class Producer implements Runnable {

    public volatile boolean canceled = false;

    BlockingQueue storage;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }


    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 10000 && !canceled) {
                if (num % 100 == 0) {

                    // 生产者程序在这里阻塞住了，线程陷入了长时间的阻塞, 那么不会往下执行, 并且重新执行while循环里面的条件
                    // 所以这里需要使用interrupt, 就可以修复无尽等待的问题
                    storage.put(num);
                    System.out.println(num + "是100的倍数,被放到仓库中了。");
                }
                num++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者结束运行");
        }
    }
}


/**
 * 消费者
 */
class Consumer {

    BlockingQueue storage ;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums() {
        if (Math.random() > 0.95) {
            return false;
        }
        return true;
    }
}


