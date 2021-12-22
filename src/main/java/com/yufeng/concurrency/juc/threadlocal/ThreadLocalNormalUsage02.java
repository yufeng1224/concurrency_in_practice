package com.yufeng.concurrency.juc.threadlocal;

/**
 * @description
 *      1. 进一步延伸: 循环创建10个线程打印日期
 *      2. 此时就会产生10个SimpleDateFormat对象
 * @author yufeng
 * @create 2020-03-15
 */
public class ThreadLocalNormalUsage02 extends AbstractThreadLocalNormal {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(() -> {
                String date = new ThreadLocalNormalUsage02().date(finalI);
                System.out.println(date);
            }).start();
            Thread.sleep(100);
        }
    }

}
