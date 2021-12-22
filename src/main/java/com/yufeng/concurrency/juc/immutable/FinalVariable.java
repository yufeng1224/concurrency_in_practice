package com.yufeng.concurrency.juc.immutable;

/**
 * @description
 *      演示类中的final变量的3种赋值时机
 *         1. 在声明变量的等号右边直接赋值
 *         2. 在构造函数中赋值
 *         3. 在类的初始代码块中赋值
 * @author yufeng
 * @create 2020-03-24
 */
public class FinalVariable {

    /** 必须赋值, 否则编译都不过 */
//    private final int a;

    /**
     * 第一种方式: 在声明变量的等号右边直接赋值
     */
    private final int a = 6;

    /**
     * 第二种方式: 在构造函数中赋值
     */
    private final int b;

    public FinalVariable() {
        b = 7;
    }

    /**
     * 第三种方式: 在类的初始代码块中赋值
     */
    private final int c;
    {
        c = 8;
    }


    public static void main(String[] args) {
        FinalVariable finalVariable = new FinalVariable();
        System.out.println(finalVariable.a);
        System.out.println(finalVariable.b);
        System.out.println(finalVariable.c);
    }
}
