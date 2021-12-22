package com.yufeng.concurrency.threadcoreknowledge.property;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      自定义线程名字
 *      在java层, 运行期间是可以修改线程的名字
 *      在native层, 线程一旦运行, 是无法再做修改的, 因为会对线程的状态进行检查;
 * @author yufeng
 * @create 2020-02-23
 */
public class ThreadName {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 20; i ++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("当前线程的名字:" + Thread.currentThread().getName());
            }
        }, "11");
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.setName("22");           // 可以在线程运行期间修改名字
    }
}
