package com.yufeng.concurrency.jcip.part1;

import com.yufeng.concurrency.jcip.annotations.GuardedBy;
import com.yufeng.concurrency.jcip.annotations.ThreadSafe;

/**
 * @description
 *      线程安全的数值序列生成器
 * @author yufeng
 * @create 2020-04-10
 */
@ThreadSafe
public class Sequence {

    @GuardedBy("this") private int nextValue;

    public synchronized  int getNext() {
        return nextValue ++;
    }

    public static void main(String[] args) {
        new Sequence().getNext();
    }
}
