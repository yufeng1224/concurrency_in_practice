package com.yufeng.concurrency.threadcoreknowledge.createthread.wrongways;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @description
 *      错误观点: 定时器也是一种新建线程的方式
 *      错误原因: TimerTask实现了Runnable接口, 所以本质上还是通过实现Runnable接口的方式
 *      创建线程。
 * @author yufeng
 * @create 2020-02-14
 */
public class TimerTaskDemo {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 1000 , 1000);
    }
}
