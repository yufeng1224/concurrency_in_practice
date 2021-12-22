package com.yufeng.concurrency.jcip.part1;

import com.yufeng.concurrency.jcip.annotations.NotThreadSafe;

/**
 * @description
 *      非线程安全的数值序列生成器
 * @author yufeng
 * @create 2020-04-10
 */
@NotThreadSafe
public class UnsafeSequence {

    private int value;

    public int getNext() {
        return value++;         // race condition: read-modify-write
    }

    public static void main(String[] args) {
        new UnsafeSequence().getNext();
    }
}
