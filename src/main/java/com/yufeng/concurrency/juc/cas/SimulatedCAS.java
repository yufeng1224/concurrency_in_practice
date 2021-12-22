package com.yufeng.concurrency.juc.cas;

/**
 * @description
 *      1. 模拟CAS操作, 等价代码
 * @author yufeng
 * @create 2020-03-22
 */
public class SimulatedCAS {

    private volatile int value;

    public synchronized int get() {
        return value;
    }

    /**
     * 1. 预期值与内存值相同时, 将内存值修改为新的值, 并返回原先的值。
     * 2. 否则直接返回原值
     */
    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }


    public synchronized boolean compareAndSet(int expectedValue, int newValue)  {
        return (expectedValue == compareAndSwap(expectedValue, newValue));
    }


    public static void main(String[] args) {
        SimulatedCAS cas = new SimulatedCAS();
        cas.value = 10;
        cas.compareAndSwap(10, 12);
        System.out.println(cas.get());      // 12

        System.out.println(cas.compareAndSet(12, 14));      // true
        System.out.println(cas.get());      // 14
    }
}
