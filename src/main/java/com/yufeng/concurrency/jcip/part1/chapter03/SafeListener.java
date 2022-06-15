package com.yufeng.concurrency.jcip.part1.chapter03;

/**
 * @description
 *     使用工厂方法来防止this引用在构造过程中逸出
 * @author yufeng
 * @create 2020-04-24
 */
public class SafeListener {

    private final EventListener listener;

    private SafeListener() {
        listener = new EventListener() {
            public void onEvent(Event e) {
                doSomething(e);
            }
        };
    }

    public static SafeListener newInstance(EventSource source) {
        SafeListener safe = new SafeListener();
        source.registerListener(safe.listener);
        return safe;
    }

    void doSomething(Event e) {
    }

    interface EventSource {
        void registerListener(EventListener e);
    }

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {
    }

}
