package com.yufeng.concurrency.jcip.part1.chapter03;

/**
 * @description
 *      1. 使内部的可变状态逸出(不要这么做)
 *      2. 按照一下方式来发布states, 就会出现问题, 因为任何调用者都能修改这个数组的内容
 * @author yufeng
 * @create 2020-04-24
 */
public class UnsafeStates {

    private String[] states = new String[]{
            "AK", "AL" /*...*/
    };

    public String[] getStates() {
        return states;
    }

}
