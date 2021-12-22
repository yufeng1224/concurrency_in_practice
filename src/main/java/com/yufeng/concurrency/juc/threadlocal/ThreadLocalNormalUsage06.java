package com.yufeng.concurrency.juc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description
 *      1. 更好的解决方案: 使用ThreadLocal, 每个线程内部创建一个独有的SimpleDateFormat对象
 *         既保证了线程安全，又高效利用了内存
 * @author yufeng
 * @create 2020-03-15
 */
public class ThreadLocalNormalUsage06 {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);


    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = ThreadSafeFormatter.dateFormatThreadLocal.get();
        return dateFormat.format(date);
    }


    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(() -> {
                String date = new ThreadLocalNormalUsage06().date(finalI);
                System.out.println(date);
            });
        }
        threadPool.shutdown();
//        String a = ThreadSafeFormatter.stringThreadLocal.get();
//        System.out.println(a);
    }

}


/**
 * 生产出线程安全的日期格式化工具
 */
class ThreadSafeFormatter extends AbstractThreadLocalNormal {

    /**
     * 获取线程内部的SimpleDateFormat对象(匿名内部类实现方式)
     */
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal
            .withInitial(() -> new SimpleDateFormat(DEFAULT_DATE_FORMAT));


    /**
     * 写法二
     */
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal2 =
            new ThreadLocal<SimpleDateFormat>(){
                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat(DEFAULT_DATE_FORMAT);
                }
            };


    /**
     * 一个线程中可以写多个threadLocal对象
     */
    public static ThreadLocal<String> stringThreadLocal = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "second threadLocal";
        }
    };

}
