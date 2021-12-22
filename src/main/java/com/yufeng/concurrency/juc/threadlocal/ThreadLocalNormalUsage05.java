package com.yufeng.concurrency.juc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description
 *      1. 演进4问题解决: 加类锁来解决线程安全的问题
 *      2. 最终结果正常, 保证了线程安全。 但是并发效率低, 高并发场景下不符合预期
 * @author yufeng
 * @create 2020-03-15
 */
public class ThreadLocalNormalUsage05 extends AbstractThreadLocalNormal {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

    @Override
    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        String s = null;

        /** 加一个类锁 */
        synchronized (ThreadLocalNormalUsage05.class) {
            s = dateFormat.format(date);
        }
        return s;
    }


    public static void main(String[] args)  {
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            threadPool.submit(() -> {
                String date = new ThreadLocalNormalUsage05().date(finalI);
                System.out.println(date);

            });
        }
        threadPool.shutdown();
    }
}
