package com.yufeng.concurrency.jcip.part1.chapter03;

import com.yufeng.concurrency.jcip.annotations.NotThreadSafe;

/**
 * @description
 *      1. 非线程安全的可变整数类
 *      2. 值失效问题: 某个线程调用了set, 那么另一个正在调用get的线程可能会看
 *         到更新后的value值, 也可能看不到
 * @author yufeng
 * @create 2020-04-24
 */
@NotThreadSafe
public class MutableInteger {

    private int value;

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }

}
