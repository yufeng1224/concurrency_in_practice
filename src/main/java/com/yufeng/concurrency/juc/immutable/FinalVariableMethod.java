package com.yufeng.concurrency.juc.immutable;

/**
 * @description
 *      演示方法中的final变量的赋值时机
 *         1. 必须在使用前赋值, 其他没有要求
 * @author yufeng
 * @create 2020-03-24
 */
public class FinalVariableMethod {

    public static void methodVariable() {
        final int f;
        /** 必须在使用前赋值*/
//        int g = f;

        f = 100;
        System.out.println(f);
    }


    public static void main(String[] args) {
        methodVariable();
    }
}
