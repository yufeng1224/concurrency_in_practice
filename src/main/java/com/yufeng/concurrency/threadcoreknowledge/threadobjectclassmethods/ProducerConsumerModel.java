package com.yufeng.concurrency.threadcoreknowledge.threadobjectclassmethods;


import java.util.Date;
import java.util.LinkedList;

/**
 * @descrption
 *      用 wait/notify 实现生产者消费者模式
 * @author yufeng
 * @create 2020-02-20
 */
public class ProducerConsumerModel {

    public static void main(String[] args) {

        EventStorage eventStorage = new EventStorage();
        Producer producer = new Producer(eventStorage);
        Consumer consumer = new Consumer(eventStorage);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}


/**
 * 生产者线程
 */
class Producer implements Runnable {

    private EventStorage storage;

    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0 ; i < 50; i ++) {
            storage.put();
        }
    }
}


/**
 * 消费者线程
 */
class Consumer implements Runnable {

    private EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.take();
        }
    }
}


class EventStorage {

    private int maxSize;

    private LinkedList<Date> storage;

    public EventStorage() {
        maxSize = 10;
        storage = new LinkedList<>();
    }

    public synchronized void put() {
        /** 仓库已满, 生产者进行等待。*/
        while (storage.size() == maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /** 没满继续放数据 */
        storage.add(new Date());
        System.out.println("仓库里有了" + storage.size() + "个产品");
        notify();
    }

    public synchronized void take() {
        /** 仓库为空, 消费者无法继续消费 */
        while (storage.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /** 进行消费 */
        System.out.println("拿到了" + storage.poll() + "，现在仓库还剩下" + storage.size());
        notify();
    }
}

