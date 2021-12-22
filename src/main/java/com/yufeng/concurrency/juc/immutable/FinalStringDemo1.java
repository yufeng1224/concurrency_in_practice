package com.yufeng.concurrency.juc.immutable;

/**
 * @description
 *      题目练习(一)
 *         1. 字符串加final关键字修饰, 编译期间就确定了(相当于常量)
 *         2. 正确理解常量池和堆的关系
 * @author yufeng
 * @create 2020-03-24
 */
public class FinalStringDemo1 {

    public static void main(String[] args) {
        String a = "wukong2";
        final String b = "wukong";          // 编译期间就确定了，相当于常量
        String d = "wukong";

        /** b和2都是常量, 编译期间就能就确定。 所以值可以确定是字符串变量"wukong2", 指向和a一样的地址 */
        String c = b + 2;
        String e = d + 2;                   // e的值会在运行时确定

        System.out.println((a == c));       // true
        System.out.println((a == e));       // false
    }
}
