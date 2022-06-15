package com.yufeng.concurrency.jcip.part1.chapter03;

import com.yufeng.concurrency.jcip.annotations.GuardedBy;
import com.yufeng.concurrency.jcip.annotations.ThreadSafe;

/**
 * @description
 *      线程安全的可变整数类
 * @author yufeng
 * @create 2020-04-24
 */
@ThreadSafe
public class SynchronizedInteger {

    @GuardedBy("this")
    private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized void set(int value) {
        this.value = value;
    }
}
