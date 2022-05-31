package com.yufeng.concurrency.juc.collections.copyonwrite;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description
 *      读取数据不一致性演示
 * @author yufeng
 * @create 2020-03-27
 */
public class CopyOnWriteArrayListDemo3 {

    public static void main(String[] args) {

        final CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("a");
        list.add("b");

        for (int i = 0; i < 5; i ++) {
            final int j = i;
            new Thread(() -> {
                try {
                    int timeout = new Random().nextInt(1000);
                    Thread.sleep(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 写入数据
                list.add("i-" + j);
                // 读取数据
                for (String str : list) {
                    System.out.println("线程-" + Thread.currentThread().getName() + ", 读取内容" + str);
                }
            }).start();

        }
    }

}
