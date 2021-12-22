package com.yufeng.concurrency.juc.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @description
 *      1. 演示Future接口的使用方法
 *      2. get(long timeout, TimeUnit unit)方法: 等待一定的时间能够正常获得结果
 * @author yufeng
 * @create 2020-03-13
 */
public class FutureDemo04 {

    private static final ExecutorService service = Executors.newFixedThreadPool(10);

    public void printAd() {
        Future<Ad> f = service.submit(new FetchAdTask());
        Ad ad = null;
        try {
            /** 等待4秒后获取广告结果, 程序能够正常输出 */
            ad = f.get(4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        service.shutdown();
        System.out.println(ad);
    }


    public static void main(String[] args) {
        FutureDemo04 timeout = new FutureDemo04();
        timeout.printAd();          // 程序正常输出
    }
}


class Ad {

    String name;

    public Ad(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "name='" + name + '\'' +
                '}';
    }
}


class FetchAdTask implements Callable<Ad> {

    @Override
    public Ad call() throws Exception {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println("sleep期间被中断了");
        }
        return new Ad("旅游订票哪家强？找某程");
    }
}

