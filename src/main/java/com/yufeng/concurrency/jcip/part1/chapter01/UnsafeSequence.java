package com.yufeng.concurrency.jcip.part1.chapter01;

import com.yufeng.concurrency.jcip.annotations.NotThreadSafe;

/**
 * @description
 *      1. 非线程安全的数值序列生成器
 *      2. 多个线程之间的交替操作会导致不可预料的结果
 * @author yufeng
 * @create 2020-04-20
 */
@NotThreadSafe
public class UnsafeSequence {

    private int value;

    public int getNext() {
        return value ++;         // race condition: read-modify-write
    }

    public static void main(String[] args) {
        new UnsafeSequence().getNext();
    }
}
