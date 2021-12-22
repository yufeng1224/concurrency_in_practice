package com.yufeng.concurrency.juc.threadlocal;

/**
 * @description
 *      ThreadLocal空指针异常代码演示
 * @author yufeng
 * @create 2020-03-15
 */
public class ThreadLocalNPE {

    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();

    public void set() {
        longThreadLocal.set(Thread.currentThread().getId());
    }


    public long get() {
        return longThreadLocal.get();
    }

    /**
     * 正确写法
     */
    public Long getLong() {
        return longThreadLocal.get();
    }


    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();

        /** 主线程设置并打印 */
        threadLocalNPE.set();
        System.out.println(threadLocalNPE.get());


        /** 子线程设置并打印 */
        Thread thread1 = new Thread(() -> {
            /**
             * 1. 此处直接get, 会报NPE
             * 2. 因为long get()这里的long是基本数据类型。而泛型类中使用的是包装类型Long。
             *    在 Long->long 装箱拆箱的过程中产生了空指针异常
             */
            System.out.println(threadLocalNPE.get());
        });
        thread1.start();


        /** 子线程设置并打印, 输出null */
        new Thread(() -> System.out.println(threadLocalNPE.getLong())).start();
    }
}
