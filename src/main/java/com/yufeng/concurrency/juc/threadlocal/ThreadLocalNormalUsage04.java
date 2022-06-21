package com.yufeng.concurrency.juc.threadlocal;

import com.yufeng.concurrency.jcip.annotations.NotThreadSafe;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @description
 *      1. 1000个打印日期的任务，用线程池来执行, 并使用同一个SimpleDateFormat对象获取日期, 节省资源
 *      2. 问题发现: 出现了重复日期!
 *      3. 原因: 当所有线程都共用同一个SimpleDateFormat对象时, 发生了线程安全问题
 * @author yufeng
 * @create 2020-03-15
 */
@NotThreadSafe
public class ThreadLocalNormalUsage04 extends AbstractThreadLocalNormal {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

    @Override
    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        return dateFormat.format(date);             // 复用同一个SimpleDateFormat对象
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(() -> {
                String date = new ThreadLocalNormalUsage04().date(finalI);
                System.out.println(date);
            });
        }
        threadPool.shutdown();
    }
}
