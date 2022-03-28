package com.yufeng.extend.innerclass.part1;

/**
 * @description
 *      局部内部类代码演示
 *          1. 局部内部类如果访问方法中的局部变量, 则这个变量是不能被更改的
 *             1-1 在Java7中, 这个变量是必须加final关键字的, 但是Java8可以不加, 但是不能做更改
 *          2. 局部内部类就像方法里面的一个局部变量一样, 是不能有public、 protected、 private以及static修饰的
 * @author yufeng
 * @create 2020-03-08
 */
public class OuterDemo02 {

    private int age = 18;

    private static String name = "OuterDemo02";

    public void methodOuter() {
        /** 局部变量无法做更改 */
        int num = 10;
        //num = 20;

        /** 成员变量和类变量可以做修改 */
        age = 20;
        name = "methodOuter";

        class InnerDemo03 {
            public void methodInner() {
                System.out.println(num);
                System.out.println(age);
                System.out.println(name);
            }
        }

        InnerDemo03 innerDemo03 = new InnerDemo03();
        innerDemo03.methodInner();
    }
}
