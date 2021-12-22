package com.yufeng.concurrency.juc.future;

import java.util.concurrent.*;

/**
 * @description
 *      1. 演示Future接口的使用方法
 *      2. isDone(): 判断任务是否执行完毕, 不管任务是正常执行结束还是抛出异常结束
 *      3. get()方法: 如果callable任务早已运行结束, 调用此方法时才会抛出异常
 * @author yufeng
 * @create 2020-03-13
 */
public class FutureDemo03 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> future = service.submit(new CallableTask());

        try {
            /** 等待中, 是为了让Callable任务运行结束。但是此时是不会抛出异常的 */
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.println(future.isDone());            // true

            /** 当调用get()方法时, 才会将异常进行抛出 */
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("InterruptedException异常");
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("ExecutionException异常");    // 输出该行语句
        }

        service.shutdown();
    }


    static class CallableTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            throw new IllegalArgumentException("Callable抛出异常");
        }
    }
}
