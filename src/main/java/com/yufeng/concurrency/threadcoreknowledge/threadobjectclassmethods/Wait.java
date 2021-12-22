package com.yufeng.concurrency.threadcoreknowledge.threadobjectclassmethods;

/**
 * @descrption
 *      展示wait和notify的基本用法
 *          1. 研究代码执行顺序;
 *          2. 证明wait是释放锁的;
 * @author yufeng
 * @create 2020-02-20
 */
public class Wait {

    public static Object object = new Object();

    static class Thread1 extends Thread {

        @Override
        public void run() {
            /** 获得monitor锁, 进入同步方法块 */
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "开始执行了");
                try {
                    Thread.sleep(200);
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "重新获取到了锁。");
            }
        }
    }


    /**
     * 从代码逻辑来看, Thread2 需要后执行
     */
    static class Thread2 extends Thread {

        @Override
        public void run() {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "开始执行了");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                object.notifyAll();
                System.out.println("线程" + Thread.currentThread().getName() + "调用了notify()") ;
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();

        /**
         * 1. thread1先运行, 进入RUNNABLE状态
         * 2. thread2还处于NEW状态
         */
        System.out.println("thread1.getState(): " + thread1.getState());
        System.out.println("thread2.getState(): " + thread2.getState());

        Thread.sleep(100);

        /**
         * 1. thread2执行start(), 从NEW -> RUNNABLE
         * 2. thread2执行run()时, 由于无法拿到监视器锁, 线程状态从RUNNABLE -> BLOCKED状态
         */
        thread2.start();
        System.out.println("thread2.getState(): " + thread2.getState());    // BLOCKED

        Thread.sleep(100);

        /** thread1已经执行了wait(), 并且释放掉了监视器锁, 进入WAITING状态 */
        System.out.println("thread1.getState(): " + thread1.getState());    // WAITING
    }
}
