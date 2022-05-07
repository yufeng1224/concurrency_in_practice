package com.yufeng.concurrency.threadcoreknowledge.threadobjectclassmethods;

import java.util.concurrent.TimeUnit;

/**
 * @descrption
 *     1. notify, notifyAll方法演示: 证明调用start()顺序不代表线程真正启动顺序
 *     2. 创建3个线程, 线程1和线程2先后被阻塞, 线程3去唤醒以上2个线程
 * @author yufeng
 * @create 2020-02-20
 */
public class WaitNotifyAll implements Runnable {

    private static final Object resourceA = new Object();

    @Override
    public void run() {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + " got resourceA lock.");
            try {
                System.out.println(Thread.currentThread().getName() + " waits to start.");
                resourceA.wait();
                TimeUnit.SECONDS.sleep(4);
                System.out.println(Thread.currentThread().getName() + "is waiting to end.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new WaitNotifyAll();

        Thread threadA = new Thread(r);
        Thread threadB = new Thread(r);
        Thread threadC = new Thread(() -> {
            synchronized (resourceA) {
                resourceA.notifyAll();              // 3个线程必须持有的是同一把锁(否则无法唤醒)
                System.out.println("ThreadC notified.");
            }
        });

        threadA.start();
        threadB.start();

        /** 这行函数不加, 可能会造成线程错乱! 因为threadC可能先执行。这也从侧面证明了start()方法的执行顺序并不代表线程的执行顺序 */
        Thread.sleep(200);
        threadC.start();

        TimeUnit.SECONDS.sleep(1);

        /** 观察所有被唤醒的线程的状态, 有一个会是BLOCKED状态, 有一个获得到锁执行SLEEP()方法变成TIMED_WAITING状态 */
        System.out.println("唤醒线程threadA的状态" + threadA.getState());
        System.out.println("唤醒线程threadB的状态" + threadB.getState());
    }
}