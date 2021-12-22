package com.yufeng.concurrency.juc.lock.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description
 *      1. ReentrantLock的基本用法
 *      2. 去掉lock后, 字符串的输出顺序会错乱
 * @author yufeng
 * @create 2020-03-17
 */
public class LockDemo {

    public static void main(String[] args) {
        new LockDemo().init();
    }


    private void init() {
        final Outputer outputer = new Outputer();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outputer.output("悟空");
            }
        }).start();


        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outputer.output("大师兄");
            }
        }).start();
    }


    static class Outputer {
        Lock lock = new ReentrantLock();

        /**
         * 字符串打印方法，一个个字符的打印
         */
        public void output(String name) {
            int len = name.length();
            //lock.lock();
            try {
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            } finally {
                //lock.unlock();
            }
        }
    }
}
