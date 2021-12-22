package com.yufeng.concurrency.juc.immutable;

/**
 * @description
 *      演示类中的static final变量的2种赋值时机
 *         1. 在声明变量的等号右边直接赋值
 *         2. 在static初始代码块中赋值
 * @author yufeng
 * @create 2020-03-24
 */
public class FinalVariableStatic {

    /** 必须赋值, 否则编译都不过 */
//    private static final int d;

    /**
     * 第一种方式: 在声明变量的等号右边直接赋值
     */
    private static final int d = 6;


    /**
     * 第二种方式: 在类的初始代码块中赋值
     */
    private static final int e;
    static {
        e = 7;
    }


    public static void main(String[] args) {
        System.out.println(FinalVariableStatic.d);
        System.out.println(FinalVariableStatic.e);
    }

}
