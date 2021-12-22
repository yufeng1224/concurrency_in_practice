package com.yufeng.concurrency.threadcoreknowledge.threadunsafe;

import java.util.HashMap;
import java.util.Map;

/**
 * @description
 *      1. 第三种线程安全问题: 对象不正确的发布;
 *      2. 案例①: 错误地发布了一个private对象(本意private对象是公共资源, 只能用作读, 不能做修改)
 *      3. 由于某个线程做了修改操作, 导致后面所有的线程查看的都是错误的数据!
 * @author yufeng
 * @create 2020-02-25
 */
public class MultiThreadsError04 {

    private Map<String, String> states;

    public MultiThreadsError04() {
        states = new HashMap<>();
        states.put("1", "周一");
        states.put("2", "周二");
        states.put("3", "周三");
        states.put("4", "周四");
        states.put("5", "周五");
        states.put("6", "周六");
        states.put("7", "周日");
    }


    /**
     * 错误的发布方式
     */
    public Map<String, String> getStates() {
        return states;
    }


    /**
     * 正确的发布方式
     *    1. 发布当前对象的副本
     *    2. 则每个线程都有了自己的副本, 对副本进行修改的话不会影响原来的states
     */
    public Map<String, String> getStatesImproved() {
        return new HashMap<>(states);
    }


    public static void main(String[] args) {
        MultiThreadsError04 multiThreadsError04 = new MultiThreadsError04();
        Map<String, String> states = multiThreadsError04.getStates();
        System.out.println(states.get("1"));
        states.remove("1");
        System.out.println(states.get("1"));                                     // 原始数据被删除了

        System.out.println(multiThreadsError04.getStatesImproved().get("1"));
        multiThreadsError04.getStatesImproved().remove("1");
        System.out.println(multiThreadsError04.getStatesImproved().get("1"));    // 原始数据还是存在
    }
}
