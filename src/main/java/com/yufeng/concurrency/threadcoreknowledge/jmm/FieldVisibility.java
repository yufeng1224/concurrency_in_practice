package com.yufeng.concurrency.threadcoreknowledge.jmm;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. 演示可见性带来的问题
 *      2. 两个线程交替执行: Thread-A执行change()函数, Thread-B执行print()函数
 *      3. 交替执行出现了四种结果组合:
 *         a = 1, b = 2;        // Thread-B先执行, Thread-A后执行
 *         a = 3, b = 2;        // Thread-A中a先赋值, Thread-B开始打印, 此时b还没赋值
 *         a = 3, b = 3;        // Thread-A先执行, Thread-B后执行
 *
 *         a = 1, b = 3;        // Thread-A先执行, Thread-B后执行, 但是由于CPU有多级缓存的缘故。 导致a虽然被修改了，但是还没有同步刷回
 *                                 到主内存中, 导致a的值对其他线程不可见!  而b已经同步到主内存中, 则对其他线程可见。
 *                                 以上就造成了这种结果
 * @author yufeng
 * @create 2020-02-27
 */
public class FieldVisibility {

    int a = 1;
    int b = 2;

    private void change() {
        a = 3;
        b = a;
    }


    private void print() {
        System.out.println("a=" + a +", b=" + b);
    }


    public static void main(String[] args) {
        while (true) {
            FieldVisibility test = new FieldVisibility();

            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.change();
            }, "Thread-A").start();

            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.print();
            }, "Thread-B").start();

            /** 测试对应的结果组合是否存在 */
            if (test.a == 1 && test.b == 3) {
                break;
            }
        }
    }
}







