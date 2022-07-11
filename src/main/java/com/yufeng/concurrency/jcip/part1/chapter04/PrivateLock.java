package com.yufeng.concurrency.jcip.part1.chapter04;

import com.yufeng.concurrency.jcip.annotations.GuardedBy;

/**
 * @description
 *      通过一个私有的锁来保护状态
 * @author yufeng
 * @create 2020-04-27
 */
public class PrivateLock {

    private final Object myLock = new Object();

    @GuardedBy("myLock") Widget widget;

    void someMethod() {
        synchronized (myLock) {
            // Access or modify the state of widget
        }
    }

}
