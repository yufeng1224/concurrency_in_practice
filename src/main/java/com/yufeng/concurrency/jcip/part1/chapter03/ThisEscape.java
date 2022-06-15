package com.yufeng.concurrency.jcip.part1.chapter03;

/**
 * @description
 *      隐式地使用this引用逸出
 * @author yufeng
 * @create 2020-04-24
 */
public class ThisEscape {

    public ThisEscape(EventSource source) {
        source.registerListener((e) -> doSomething(e));
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

