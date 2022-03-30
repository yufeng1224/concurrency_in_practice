package com.yufeng.concurrency.threadcoreknowledge.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      必定发生死锁的案例演示
 *         1. 当类的对象flag=1时(T1), 先锁定O1, 睡眠1秒, 然后准备获取锁O2
 *         2. T1在睡眠的时候, 另一个flag=0对象(T2)线程启动, 先锁定O2, 睡眠1秒, 等待T1释放O1
 *         3. T1睡眠结束后需要锁定O2才能继续执行, 而此时O2已被T2锁定
 *         4. T2睡眠结束后需要锁定O1才能继续执行, 而此时O1已被T1锁定
 *         5. T1、T2相互等待, 都需要对方锁定的资源才能继续执行, 从而死锁
 *         6. 退出信号: Process finished with exit code 130 (interrupted by signal 2: SIGINT)
 *            这是不正常退出的信号, 正常结束的程序信号时0
 * @author yufeng
 * @create 2020-03-04
 */
public class DeadLock01 implements Runnable {

    int flag = 1;

    static Object o1 = new Object();
    static Object o2 = new Object();

    @Override
    public void run() {
        System.out.println("flag = " + flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("Thread-A成功拿到两把锁");
                }
            }
        }

        if (flag == 0) {
            synchronized (o2) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("Thread-B成功拿到两把锁");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock01 r1 = new DeadLock01();
        DeadLock01 r2 = new DeadLock01();
        r1.flag = 1;
        r2.flag = 0;

        Thread t1 = new Thread(r1, "Thread-A");
        Thread t2 = new Thread(r2, "Thread-B");
        t1.start();
        t2.start();
    }
}
