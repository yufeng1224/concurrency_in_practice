package com.yufeng.concurrency.juc.threadlocal;


import com.yufeng.concurrency.jcip.annotations.ThreadSafe;

/**
 * @description
 *      两个线程分别用自己的SimpleDateFormat对象打印日期
 * @author yufeng
 * @create 2020-03-15
 */
@ThreadSafe
public class ThreadLocalNormalUsage01 extends AbstractThreadLocalNormal {

    public static void main(String[] args) {
        new Thread(() -> {
            String date = new ThreadLocalNormalUsage01().date(10);
            System.out.println(date);
        }).start();

        new Thread(() -> {
            String date = new ThreadLocalNormalUsage01().date(7200);
            System.out.println(date);
        }).start();
    }
}
