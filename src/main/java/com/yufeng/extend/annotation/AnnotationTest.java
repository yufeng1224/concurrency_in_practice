package com.yufeng.extend.annotation;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 *      注解案例演示
 * @author yufeng
 * @create 2020-04-08
 */
public class AnnotationTest {

    /**
     * @Override: 重写的注解
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * @Deprecated: 虽然可以使用, 但是不推荐。可能存在更好的方式
     */
    @Deprecated
    public static void test01() {
        System.out.println("Deprecated method()");
    }

    /**
     * @SuppressWarnings("all"): 压制警告
     */
    @SuppressWarnings("all")
    public void test02() {
        List list = new ArrayList();
    }

    @MyAnnotation
    public static void test03() {
        System.out.println("MyAnnotation");
    }

    public static void main(String[] args) {
        test01();
        test03();
    }
}
