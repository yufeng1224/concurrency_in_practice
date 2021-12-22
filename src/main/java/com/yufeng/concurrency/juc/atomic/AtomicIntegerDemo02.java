package com.yufeng.concurrency.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description
 *      1. AtomicInteger用法演示
 *      2. getAndIncrement()和incrementAndGet()的区别
 *         2-1 getAndIncrement()赋值之后返回的是原值, 类似于i++
 *         2-2 incrementAndGet()赋值之后返回的是原值+1, 类似于++i
 *      3. getAndAdd()和addAndGet()方法同2类似
 * @author yufeng
 * @create 2020-03-20
 */
public class AtomicIntegerDemo02 {

    public static void show() {
        AtomicInteger atomicInteger = new AtomicInteger();

        /** 1. AtomicInteger 默认初始化值是0 */
        int initValue = atomicInteger.get();
        System.out.println(initValue);                                // 0

        /** 2. 如果当前值 == 预期值, 则以原子方式将值设置为给定的新值 */
        boolean flag = atomicInteger.compareAndSet(0, 10);
        System.out.println(flag);
        System.out.println("新值为: " + atomicInteger.get());          // 10

        /** 3. 以原子方式将当前值加1, 并返回原值 */
        initValue = atomicInteger.getAndIncrement();
        System.out.println(initValue);                                // 10
        System.out.println(atomicInteger.get());                      // 11

        /** 4. 以原子方式将当前值加1, 并返回自增后的值 */
        initValue = atomicInteger.incrementAndGet();
        System.out.println(initValue);                                // 12
        System.out.println(atomicInteger.get());                      // 12

        /** 5. 以原子方式将当前值加n, 并返回原值 */
        initValue = atomicInteger.getAndAdd(10);
        System.out.println(initValue);                                // 12
        System.out.println(atomicInteger.get());                      // 22

        /** 6. 以原子方式将当前值加n, 并返回原值+n */
        initValue = atomicInteger.addAndGet(10);
        System.out.println(initValue);                                // 32
        System.out.println(atomicInteger.get());                      // 32
    }


    public static void main(String[] args) {
        show();
    }
}
