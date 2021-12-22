package com.yufeng.concurrency.juc.immutable;

/**
 * @description
 *      1. 演示不可变的对象
 *      2. 其他类无法修改这个对象, public也不行
 *      3. 具有不变性的对象一定是线程安全的, 我们不需要对其采取'任何额外的安全措施', 也能保证线程安全
 * @author yufeng
 * @create 2020-03-24
 */
public class Person {

    public final int age = 18;

    public final String name = "Alice";

    /**
     * 1. score属性可变
     * 2. 只要有任一属性可变, 那么该对象就是可变的
     */
//    int socre = 0;


    public static void main(String[] args) {
        Person person = new Person();
//        person.age = 19;                    // 不可修改
//        person.name = "aaa";                // 不可修改
        System.out.println(person.age);
    }
}

