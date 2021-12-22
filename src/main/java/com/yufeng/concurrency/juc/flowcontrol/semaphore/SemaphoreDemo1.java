package com.yufeng.concurrency.juc.flowcontrol.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description
 *      演示Semaphore用法
 * @author yufeng
 * @create 2020-03-29
 */
public class SemaphoreDemo1 {

    /** 只发放3个许可证 */
    static Semaphore semaphore = new Semaphore(3, true);

    static class Task implements Runnable {

        @Override
        public void run() {
            try {
                semaphore.acquire();
                // semaphore.acquire(3);           // 表示一次执行需要拿掉3个许可证 ——> 可以根据负载分配权重

                System.out.println(Thread.currentThread().getName() + "拿到了许可证");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "释放了许可证");
            semaphore.release();
        }
    }


    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 100; i++) {
            service.submit(new Task());
        }
        service.shutdown();
    }

}
