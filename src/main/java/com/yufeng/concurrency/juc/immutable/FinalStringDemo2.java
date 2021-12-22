package com.yufeng.concurrency.juc.immutable;

/**
 * @description
 *      题目练习(二)
 * @author yufeng
 * @create 2020-03-24
 */
public class FinalStringDemo2 {

    public static void main(String[] args) {
        String a = "wukong2";
        final String b = getDashixiong();       // 通过方法拿到的, 编译期间无法确定b的值
        String c = b + 2;
        System.out.println(a == c);             // false

    }

    private static String getDashixiong() {
        return "wukong";
    }
}
