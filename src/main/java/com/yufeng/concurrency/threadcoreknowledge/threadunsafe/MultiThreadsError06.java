package com.yufeng.concurrency.threadcoreknowledge.threadunsafe;

/**
 * @description
 *      第三种线程安全问题: 对象不正确的发布
 *      案例③: 观察者模式下, 匿名内部类直接持有外部类的引用;
 * @author yufeng
 * @create 2020-02-25
 */
public class MultiThreadsError06 {

    int count;

    public MultiThreadsError06(MySource source) {
        source.registerListener( e -> System.out.println("\n我得到的数字是" + count));

        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }


    public static void main(String[] args) {
        MySource mySource = new MySource();
        new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySource.eventCome(new Event() {});
        }).start();
        MultiThreadsError06 error06 = new MultiThreadsError06(mySource);
    }
}


interface Event {}

interface EventListener {
    void onEvent(Event e);
}

class MySource {
    private EventListener listener;

    void registerListener(EventListener eventListener) {
        this.listener = eventListener;
    }

    void eventCome(Event e) {
        if (listener != null) {
            listener.onEvent(e);
        } else {
            System.out.println("还未初始化完毕");
        }
    }
}




