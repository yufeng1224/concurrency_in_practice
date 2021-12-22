package com.yufeng.concurrency.threadcoreknowledge.synchronize.base;

/**
 * @description
 *      反编译synchronized关键字, 观察字节码
 *         1. javap -verbose SynchronizedDecompile.class
 * @author yufeng
 * @create 2020-02-21
 */
public class SynchronizedDecompile {

    private Object lock = new Object();

    public void insert() {
        synchronized (lock) {

        }
    }
}

/**
 {
 public com.yufeng.concurrency.threadcoreknowledge.synchronize.base.SynchronizedDecompile();
 descriptor: ()V
 flags: ACC_PUBLIC
 Code:
 stack=3, locals=1, args_size=1
 0: aload_0
 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 4: aload_0
 5: new           #2                  // class java/lang/Object
 8: dup
 9: invokespecial #1                  // Method java/lang/Object."<init>":()V
 12: putfield      #3                  // Field com.yufeng.concurrency.juc.lock:Ljava/lang/Object;
 15: return
 LineNumberTable:
 line 9: 0
 line 11: 4

 public void insert();
 descriptor: ()V
 flags: ACC_PUBLIC
 Code:
 stack=2, locals=3, args_size=1
 0: aload_0
 1: getfield      #3                  // Field com.yufeng.concurrency.juc.lock:Ljava/lang/Object;
 4: dup
 5: astore_1
 6: monitorenter
 7: aload_1
 8: monitorexit
 9: goto          17
 12: astore_2
 13: aload_1
 14: monitorexit
 15: aload_2
 16: athrow
 17: return
 Exception table:
 from    to  target type
 7     9    12   any
 12    15    12   any
 LineNumberTable:
 line 14: 0
 line 16: 7
 line 17: 17
 StackMapTable: number_of_entries = 2
 frame_type = 255
 */
