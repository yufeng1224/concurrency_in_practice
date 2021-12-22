package com.yufeng.concurrency.juc.lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description
 *      乐观锁、悲观锁代码演示
 * @author yufeng
 * @create 2020-03-17
 */
public class PessimismOptimismLock {

    static int a;

    public static void main(String[] args) {
        /** 乐观锁的实现: 原子类 */
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();

        /** 悲观锁 */
        testMethod();
    }


    /**
     * 悲观锁的实现
     */
    public static synchronized void testMethod() {
        a ++;
    }
}
