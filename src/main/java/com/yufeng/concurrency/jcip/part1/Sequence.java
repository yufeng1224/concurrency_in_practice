package com.yufeng.concurrency.jcip.part1;

import com.yufeng.concurrency.jcip.annotations.GuardedBy;
import com.yufeng.concurrency.jcip.annotations.ThreadSafe;

/**
 * @description
 *      1. 线程安全的数值序列生成器
 *      2. 解决方式: 使用synchronize对共享变量的访问操作进行协同, 防止互相干扰
 * @author yufeng
 * @create 2020-04-20
 */
@ThreadSafe
public class Sequence {

    @GuardedBy("this") private int nextValue;

    public synchronized int getNext() {
        return nextValue ++;
    }

    public static void main(String[] args) {
        new Sequence().getNext();
    }
}
