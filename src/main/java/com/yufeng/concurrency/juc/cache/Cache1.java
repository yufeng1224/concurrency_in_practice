package com.yufeng.concurrency.juc.cache;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @description
 *      1. 最简单的缓存形式: HashMap
 *      2. 使用synchronized和final关键字保证线程安全
 *      3. 缺点: 使用synchronized性能差、代码复用能力差。
 * @author yufeng
 * @create 2020-04-05
 */
public class Cache1 {

    private final HashMap<String,Integer> cache = new HashMap<>();

    public synchronized Integer computer(String userId) throws InterruptedException {
        Integer result = cache.get(userId);
        /** 先检查HashMap里面有没有保存过之前的计算结果 */
        if (result == null) {
            /**
             * 1. 如果缓存中找不到, 那么需要现在计算一下结果, 并且保存到HashMap中
             * 2. 在实际的生产中, 这种计算可能就是数据库中的慢查询, 缓存就能够发挥较大作用
             */
            result = doCompute(userId);
            cache.put(userId, result);
        }
        return result;
    }


    private Integer doCompute(String userId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return new Integer(userId);
    }


    public static void main(String[] args) throws InterruptedException {
        Cache1 cache1 = new Cache1();
        System.out.println("开始计算了");
        Integer result = cache1.computer("13");
        System.out.println("第一次计算结果：" + result);

        result = cache1.computer("13");
        System.out.println("第二次计算结果：" + result);
    }
}
