package com.yufeng.extend.bitoperation;

/**
 * @description
 *      &、|、^位运算符代码演示
 * @author yufeng
 * @create 2020-02-22
 */
public class BitOperation {

    public static void main(String[] args) {
        int num1 = 1 & 1;
        int num2 = 1 | 0;

        /** 异或: 数值相同时0, 不同时1 */
        int num3 = 1 ^ 1;
        int num4 = 0 ^ 1;
        int num5 = 0 ^ 0;

        System.out.println(num1);
        System.out.println(num2);
        System.out.println(num3);
        System.out.println(num4);
        System.out.println(num5);
    }
}
