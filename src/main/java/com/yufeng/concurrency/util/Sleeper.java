package com.yufeng.concurrency.util;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      线程睡眠工具类
 * @author yufeng
 * @create 2020-07-01
 */
public class Sleeper {

    public static void sleep(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(double i) {
        try {
            TimeUnit.MILLISECONDS.sleep((int) (i * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
