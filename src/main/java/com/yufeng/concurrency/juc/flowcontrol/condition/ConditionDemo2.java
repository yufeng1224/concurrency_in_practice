package com.yufeng.concurrency.juc.flowcontrol.condition;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description
 *      演示用Condition实现生产者消费者模式
 * @author yufeng
 * @create 2020-03-30
 */
public class ConditionDemo2 {

    private int queueSize = 10;

    private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);

    private Lock lock = new ReentrantLock();

    private Condition produce = lock.newCondition();

    private Condition consume = lock.newCondition();

    public static void main(String[] args) {
        ConditionDemo2 conditionDemo2 = new ConditionDemo2();
        Producer producer = conditionDemo2.new Producer();
        Consumer consumer = conditionDemo2.new Consumer();
        producer.start();
        consumer.start();
    }


    /**
     * 生产者
     */
    class Consumer extends Thread {

        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                lock.lock();
                try {
                    /** 1. 没有数据, 则消费停止*/
                    while (queue.size() == 0) {
                        System.out.println("队列空，等待数据");
                        try {
                            consume.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    /** 2. 有数据则取出消费, 并通知生产者可以继续生产 */
                    queue.poll();
                    produce.signalAll();
                    System.out.println("从队列里取走了一个数据，队列剩余" + queue.size() + "个元素");
                } finally {
                    lock.unlock();
                }
            }
        }
    }


    /**
     * 消费者
     */
    class Producer extends Thread {

        @Override
        public void run() {
            produce();
        }


        private void produce() {
            while (true) {
                lock.lock();
                try {
                    /** 1. 数据满了, 则生产停止 */
                    while (queue.size() == queueSize) {
                        System.out.println("队列满，等待有空余");
                        try {
                            produce.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    /** 2. 数据没满则生产数据, 并通知消费者有数据可消费 */
                    queue.offer(1);
                    consume.signalAll();
                    System.out.println("向队列插入了一个元素，队列剩余空间" + (queueSize - queue.size()));
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
