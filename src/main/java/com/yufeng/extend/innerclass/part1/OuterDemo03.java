package com.yufeng.extend.innerclass.part1;

/**
 * @description
 *      静态内部类代码演示
 *         1. 不能调用外部类的非静态成员变量和方法
 * @author yufeng
 * @create 2020-03-08
 */
public class OuterDemo03 {

    int age = 10;

    private static String name = "yufeng";

    private static void outerMethod() {
        System.out.println("外部类静态方法");
    }

    static class InnerDemo04 {
        public void innerMethod() {
            System.out.println(name);

            //System.out.println(age);      // 无法调用
            outerMethod();
        }
    }
}
