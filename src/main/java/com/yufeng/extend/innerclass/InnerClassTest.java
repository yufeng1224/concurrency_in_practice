package com.yufeng.extend.innerclass;

import org.junit.Test;

/**
 * @description
 *      单元测试案例
 * @author yufeng
 * @create 2020-03-09
 */
public class InnerClassTest {


    /**
     * InnerDemo01修饰符定义为public, 外界可以通过
     *     外部类.内部类 对象名 = 外部类对象.内部类对象;
     * 直接创建内部类对象访问
     */
    @Test
    public void outerDemo01Test01() {
        OuterDemo01.InnerDemo01 innerDemo01 = new OuterDemo01().new InnerDemo01();
        innerDemo01.show01();

    }


    /**
     * InnerDemo02修饰符定义为private, 则只能通过
     *    外部类 对象名 = new 外部类对象;
     *    对象名.方法名()
     *
     * 通过外部类对象中的方法, 创建内部类对象, 再访问内部类。
     */
    @Test
    public void outerDemo01Test02() {
        OuterDemo01 outerDemo01 = new OuterDemo01();
        outerDemo01.invokeInnerClassMethod();
    }


    /**
     * 局部内部类测试案例
     */
    @Test
    public void outerDemo02Test01() {
        OuterDemo02 outerDemo02 = new OuterDemo02();
        outerDemo02.methodOuter();
    }


    /**
     * 静态内部类
     */
    @Test
    public void outerDemo03Test01() {
        OuterDemo03.InnerDemo04 innerDemo04 = new OuterDemo03.InnerDemo04();
        innerDemo04.innerMethod();
    }


    /**
     * 匿名内部类
     *    1. 使用匿名内部类, 用接口对象接收
     */
    @Test
    public void anonymousClassTest01() {
        MyInterface obj = new MyInterface() {
            @Override
            public void method1() {
                System.out.println("匿名内部类method1");
            }

            @Override
            public void method2() {
                System.out.println("匿名内部类method2");
            }
        };
        obj.method1();
        obj.method2();
    }


    /**
     * 匿名内部类
     *    1. 直接使用匿名内部类, 并省略对象名称
     */
    @Test
    public void anonymousClassTest02() {
        new MyInterface() {
            @Override
            public void method1() {
                System.out.println("匿名内部类method1");
            }

            @Override
            public void method2() {
                System.out.println("匿名内部类method2");
            }
        }.method1();
    }
}
