package com.yufeng.concurrency.juc.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @description
 *      用AQS自定义实现一个简单的线程协作器
 *          1. 设计一个类, 想好协作的逻辑, 实现获取/释放方法
 *          2. 内部写一个Sync 类继承 AbstractQueuedSynchronizer
 *          3. 独占重写 tryAcquire()和tryRelease()函数
 *             非独占重写 tryAcquireShared()和tryReleaseShared()函数
 * @author yufeng
 * @create 2020-04-02
 */
public class OneShotLatch {

    private final Sync sync = new Sync();

    public void signal() {
        sync.releaseShared(0);
    }

    public void await() {
        sync.acquireShared(0);
    }


    private class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected int tryAcquireShared(int arg) {
            return (getState() == 1) ? 1 : -1;
        }


        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }


    public static void main(String[] args) {
        OneShotLatch oneShotLatch = new OneShotLatch();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "尝试获取latch, 获取失败就等待");
                oneShotLatch.await();
                System.out.println("开闸放行" + Thread.currentThread().getName() + "继续运行");
            }).start();
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        oneShotLatch.signal();
    }

}
