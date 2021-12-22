package com.yufeng.extend.innerclass;

/**
 * @descrption
 *      成员内部类代码演示
 * @author yufeng
 * @create 2020-03-09
 */
public class OuterDemo01 {

    private int num = 10;

    private static final String CLASS_NAME = "outerDemo";

    /** 重名字段 */
    private int age = 18;


    /**
     * 成员内部类——public修饰
     */
    public class InnerDemo01 {
        /** 重名字段 */
        private int age = 20;

        public void show01() {
            System.out.println(num);            // 直接访问外部类成员变量
            System.out.println(CLASS_NAME);     // 直接访问外部类静态变量

            /** 重名字段调用演示 */
            System.out.println(this.age);                   // 20
            System.out.println(OuterDemo01.this.age);       // 18
        }
    }


    /**
     * 成员内部类——private修饰
     */
    private class InnerDemo02 {
        public void show02() {
            System.out.println(num);
            System.out.println(CLASS_NAME);
        }
    }


    public void invokeInnerClassMethod() {
//        show();                               // 无法直接访问内部类方法
//        InnerDemo02 innerDemo02 = new InnerDemo02();
        InnerDemo02 innerDemo02 = getInnerDemo02();
        innerDemo02.show02();
    }


    /**
     * 调用成员内部类————更加规范的写法
     */
    public InnerDemo02 getInnerDemo02() {
        return new InnerDemo02();
    }
}
