package com.yufeng.concurrency.threadcoreknowledge.createthread.wrongways;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.FutureTask;

/**
 * @description
 *      FutureTask 案例演示
 * @author yufeng
 * @create 2020-07-02
 */
@Slf4j(topic = "c.FutureTaskDemo")
public class FutureTaskDemo {

    public static void main(String[] args) throws Exception {
        // 创建任务对象
        FutureTask<Integer> task3 = new FutureTask<>(() -> {
            log.debug("hello");
            return 100;
        });

        // 参数1是任务对象; 参数2是线程名字
        new Thread(task3, "t3").start();

        // 主线程阻塞, 同步等待task执行完毕的结果
        Integer result = task3.get();
        log.debug("结果是:{}", result);
    }
}
