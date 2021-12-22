package com.yufeng.concurrency.juc.cache.computable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @description
 *      耗时计算的实现类，有概率计算失败
 * @author yufeng
 * @create 2020-04-05
 */
public class MayFail implements Computable<String, Integer>{

    @Override
    public Integer compute(String arg) throws Exception {
        double random = Math.random();
        if (random > 0.5) {
            throw new IOException("读取文件出错");
        }
        /** 模拟耗时计算, 所以进行3秒钟的休眠 */
        TimeUnit.SECONDS.sleep(3);
        return Integer.valueOf(arg);
    }
}
