package com.yufeng.concurrency.jcip.part1.chapter04;

import com.yufeng.concurrency.jcip.annotations.GuardedBy;
import com.yufeng.concurrency.jcip.annotations.ThreadSafe;

/**
 * @description
 *      使用Java监视器模式的线程安全计数器
 * @author yufeng
 * @create 2020-04-27
 */
@ThreadSafe
public class Counter {

    @GuardedBy("this")
    private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment() {
        if (value == Long.MAX_VALUE)
            throw new IllegalStateException("counter overflow");
        return ++value;
    }

}
