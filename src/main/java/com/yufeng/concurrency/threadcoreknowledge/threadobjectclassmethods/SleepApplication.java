package com.yufeng.concurrency.threadcoreknowledge.threadobjectclassmethods;

/**
 * @description
 *      1. 防止CPU占用100%
 *      2. 在没有利用cpu来计算时, 不要让while(true)空转浪费cpu
 *      3. 使用yield或 sleep来让出cpu的使用权给其他程序
 * @author yufeng
 * @create 2020-02-20
 */
public class SleepApplication {

    public static void main(String[] args) {
        while (true) {
            try {
                /**
                 * 1. 可以用wait或条件变量达到类似的效果
                 * 2. 不同的是, 后两种都需要加锁, 并且需要相应的唤醒操作, 一般适用于要进行同步的场景
                 * 3. sleep 适用于无需锁同步的场景
                 */
                Thread.sleep(50);           // 不加此行代码会导致CPU占用100%
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
