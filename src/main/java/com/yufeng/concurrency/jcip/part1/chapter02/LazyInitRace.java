package com.yufeng.concurrency.jcip.part1.chapter02;

import com.yufeng.concurrency.jcip.annotations.NotThreadSafe;

/**
 * @description
 *      1. 懒加载中的竞态条件演示
 *      2. 使用"先检查后执行"的一种常见情况就是延迟初始化。延迟初始化的目的是将对象的初始化操作
 *         推迟到实际使用时才进行, 同时要确保只被初始化一次
 *      3. 假定 LazyInitRace 被用于初始化应用程序范围内的注册表, 如果在多次调用中返回不同的
 *         实例, 那么可能会丢失部分注册信息或者出现不一致的视图等等
 * @author yufeng
 * @create 2020-04-21
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
