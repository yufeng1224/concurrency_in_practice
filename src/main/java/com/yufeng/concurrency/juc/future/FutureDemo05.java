package com.yufeng.concurrency.juc.future;

import java.util.concurrent.*;

/**
 * @description
 *      1. 演示Future接口的使用方法
 *      2. get(long timeout, TimeUnit unit)方法: 任务还未执行完毕, 等待一定的时间不能正常获得结果。
 *         则会抛出TimeoutException
 *      3. 超时后处理：调用cancel(true), 代表中断正在执行的任务
 *         cancel传入true和false的区别: 代表是否中断正在执行的任务
 * @author yufeng
 * @create 2020-03-13
 */
public class FutureDemo05 {

    private static final ExecutorService service = Executors.newFixedThreadPool(10);

    public void printAd() {
        Future<Ad> f = service.submit(new FetchAdTask());
        Ad ad = null;
        try {
            /** 等待2秒后获取广告结果, 程序无法及时获得, 抛出TimeOutException */
            ad = f.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            ad = new Ad("InterruptedException 默认广告");
        } catch (ExecutionException e) {
            e.printStackTrace();
            ad = new Ad("ExecutionException 默认广告");
        } catch (TimeoutException e) {
            e.printStackTrace();
            ad = new Ad("TimeoutException 默认广告");
            System.out.println("超时，未获取到广告");

            /** 尝试中断线程 */
            boolean cancel = f.cancel(true);
            System.out.println("cancel的结果：" + cancel);
        }
        service.shutdown();
        System.out.println(ad);
    }


    public static void main(String[] args) {
        FutureDemo05 timeout = new FutureDemo05();
        timeout.printAd();          // 输出抛出超时异常后的广告
    }
}
