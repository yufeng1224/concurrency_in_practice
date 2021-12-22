package com.yufeng.concurrency.jcip.part1;

import com.yufeng.concurrency.jcip.annotations.NotThreadSafe;

/**
 * @description
 *      1. 懒加载中的竞态条件演示
 *      2. 假定 LazyInitRace 被用于初始化应用程序范围内的注册表, 如果在多次调用中返回不同的
 *         实例, 那么可能会丢失部分注册信息或者出现不一致的视图等等
 * @author yufeng
 * @create 2020-04-10
 */
@NotThreadSafe
public class LazyInitRace {

    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        if (instance == null) {
            instance = new ExpensiveObject();
        }
        return instance;
    }


    public static void main(String[] args) {

    }
}


class ExpensiveObject{}
