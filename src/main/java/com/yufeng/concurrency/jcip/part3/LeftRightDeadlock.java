package com.yufeng.concurrency.jcip.part3;

/**
 * @description
 *      案例演示: 简单的锁顺序死锁
 * @author yufeng
 * @create 2020-03-06
 */
public class LeftRightDeadlock {

    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() {
        synchronized (left) {
            synchronized (right) {
                doSomething();
            }
        }
    }

    public void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                doSomethingElse();
            }
        }
    }

    void doSomething() {}

    void doSomethingElse(){}

}
