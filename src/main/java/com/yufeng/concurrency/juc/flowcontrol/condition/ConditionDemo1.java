package com.yufeng.concurrency.juc.flowcontrol.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description
 *      1. 演示Condition的基本用法
 *      2. 主线程调用method1, 处于沉睡状态
 *      3. 子线程调用method2, 并唤醒主线程
 * @author yufeng
 * @create 2020-03-30
 */
public class ConditionDemo1 {

    private ReentrantLock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void method1() {
        lock.lock();
        try {
            System.out.println("条件不满足, 开始await");
            condition.await();
            System.out.println("条件满足了, 开始执行后续的任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void method2() {
        lock.lock();
        try {
            System.out.println("准备工作完成, 唤醒其它的线程");
            condition.signal();
        } finally {
            System.out.println("当前线程先执行完并解锁");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionDemo1 conditionDemo1 = new ConditionDemo1();
        /** 注意顺序: 写在这里主线程会直接卡死, 后面子线程将不再执行 */
        // conditionDemo1.method1();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                conditionDemo1.method2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        conditionDemo1.method1();
    }
}
