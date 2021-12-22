package com.yufeng.concurrency.juc.immutable;

/**
 * @description
 *      演示final的方法
 * @author yufeng
 * @create 2020-03-24
 */
public class FinalMethod {

    public void drink() {

    }

    /**
     *final修饰的方法, 子类不能override
     */
    public final void eat() {

    }

    /**
     * 1. 引申: static方法不能被重写
     * 2. 但是子类可以写重名的方法
     */
    public static void sleep() {

    }


//    public final static void show() {
//
//    }

}

class SubClass extends FinalMethod {

    @Override
    public void drink() {
        super.drink();
        eat();
    }

    /** 只要方法名=final修饰的方法名, 就会编译报错! */
//    public int eat() {
//        return 1;
//    }

//    public final void eat() {
//
//    }

    /**
     * 两个不同类对象的静态方法
     */
    public static void sleep() {

    }
}