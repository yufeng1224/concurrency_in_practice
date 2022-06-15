package com.yufeng.concurrency.jcip.part1.chapter03;

/**
 * @description
 *      数绵羊
 * @author yufeng
 * @create 2020-04-24
 */
public class CountingSheep {

    volatile boolean asleep;

    void tryToSleep() {
        while (!asleep) {
            countSomeSheep();
        }
    }

    void countSomeSheep() {
        // One, two, three...
    }

}
