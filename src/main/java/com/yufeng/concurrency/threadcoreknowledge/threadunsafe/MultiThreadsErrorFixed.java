package com.yufeng.concurrency.threadcoreknowledge.threadunsafe;

/**
 * @description
 *      1. 用工厂模式修复观察者模式中的对象发布问题
 *      2. 用工厂模式注册上去，然后再发布
 * @author yufeng
 * @create 2020-02-25
 */
public class MultiThreadsErrorFixed {

    int count;

    private EventListener listener;


    /**
     * 构造方法用private保护起来
     */
    private MultiThreadsErrorFixed(MySource source) {
        listener = e -> System.out.println("\n我得到的数字是" + count);

        for (int i = 0; i < 100; i++) {
            System.out.print(i);
        }
        count = 100;
    }


    /**
     * 工厂方法
     */
    public static MultiThreadsErrorFixed getInstance(MySource source) {
        MultiThreadsErrorFixed safeListener = new MultiThreadsErrorFixed(source);
        source.registerListener(safeListener.listener);
        return safeListener;
    }


    public static void main(String[] args) {
        MySource mySource = new MySource();
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySource.eventCome(new Event() {});
        }).start();

        MultiThreadsErrorFixed errorFixed = new MultiThreadsErrorFixed(mySource);
        System.out.println(errorFixed.count);
    }
}







