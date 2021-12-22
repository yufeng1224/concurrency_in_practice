package com.yufeng.concurrency.juc.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @description
 *      1. 演示AtomicBoolean的基本用法
 *      2. AtomicBoolean的作用: 可以用原子的方式更新布尔值
 * @author yufeng
 * @create 2020-03-20
 */
public class AtomicBooleanDemo {

    private static AtomicBoolean initialized = new AtomicBoolean(false);

    public static void main(String[] args) {
        /** 1. 如果当前值等于预期值, 则将该值原子设置为给定的更新值 */
        initialized.compareAndSet(false, true);

        /** 2. 获取当前布尔值 */
        boolean newBoolean = initialized.get();
        System.out.println(newBoolean);                                 // true

        /** 3. 原子设定新值, 返回原来的值 */
        boolean oldValue = initialized.getAndSet(false);
        System.out.println(oldValue);                                   // true
        System.out.println(initialized.get());                          // false

        /** 4. 最终设定为给定值 */
        initialized.lazySet(true);
        System.out.println(initialized.get());                          // true

        /** 5. 设置为给定的值 */
        initialized.set(false);
        System.out.println(initialized.get());                          // false

        /** 6. 如果当前值等于预期值, 则将该值原子为给定的更新值 */
        initialized.weakCompareAndSet(false, true);
        System.out.println(initialized.get());                          // true

    }
}
