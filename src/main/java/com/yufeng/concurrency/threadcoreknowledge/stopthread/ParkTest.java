package com.yufeng.concurrency.threadcoreknowledge.stopthread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @description
 *      park打断线程代码演示
 * @author yufeng
 * @create 2020-02-18
 */
@Slf4j(topic = "c.ParkTest")
public class ParkTest {

    private static void test4() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                log.debug("park...");
                LockSupport.park();
                log.debug("打断状态：{}", Thread.interrupted());     // 清除了中断状态, 可以继续阻塞
            }
        });
        t1.start();

        TimeUnit.SECONDS.sleep(3);
        t1.interrupt();
    }

    private static void test3() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("park...");
            LockSupport.park();
            log.debug("unpark...");
            log.debug("打断状态：{}", Thread.currentThread().isInterrupted());

            LockSupport.park();
            log.debug("不能继续阻塞了...");

        }, "t1");
        t1.start();

        TimeUnit.SECONDS.sleep(3);
        t1.interrupt();

    }

    public static void main(String[] args) throws InterruptedException {
        test3();
        test4();
    }
}
