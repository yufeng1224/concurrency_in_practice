package com.yufeng.concurrency.threadcoreknowledge.threadunsafe;

/**
 * @description
 *      1. 第三种线程安全问题: 对象不正确的发布;
 *      2. 案例②: 对象初始化还未完成, 就被其他线程所见, 导致了对象逸出
 * @author yufeng
 * @create 2020-02-25
 */
public class MultiThreadsError05 {

    static Point point;

    public static void main(String[] args) throws InterruptedException {
        new PointMaker().start();
        Thread.sleep(10);                   // 打印数据为(1,0), 对象逸出导致数据不准备
        if (point != null) {
            System.out.println(point);
        }
    }
}


class Point {

    private final int x, y;

    public Point(int x, int y) throws InterruptedException {
        this.x = x;

        /** 此处就被其它线程所见, 存在隐患! */
        MultiThreadsError05.point = this;
        Thread.sleep(100);

        this.y = y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}


class PointMaker extends Thread {

    @Override
    public void run() {
        try {
            new Point(1, 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
