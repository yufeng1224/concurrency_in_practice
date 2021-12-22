package com.yufeng.concurrency.juc.future;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @description
 *      1. 演示批量提交任务时，用List来批量接收结果
 * @author yufeng
 * @create 2020-03-13
 */
public class FutureDemo06 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        ArrayList<Future> futures = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Future<Integer> future = service.submit(new CallableTask());
            futures.add(future);
        }

        // TimeUnit.SECONDS.sleep(5);

        for (int i = 0; i < 20; i++) {
            Future<Integer> future = futures.get(i);
            try {
                Integer integer = future.get();
                System.out.println(integer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }


    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            TimeUnit.SECONDS.sleep(2);
            return new Random().nextInt(200);
        }
    }
}
