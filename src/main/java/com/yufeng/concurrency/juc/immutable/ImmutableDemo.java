package com.yufeng.concurrency.juc.immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * @description
 *      1. 一个属性是对象, 但是还是可以做到整体不可变。 因为其他类无法修改set里面的数据
 * @author yufeng
 * @create 2020-03-24
 */
public class ImmutableDemo {

    private final Set<String> students = new HashSet<>();

    public ImmutableDemo() {
        students.add("李小美");
        students.add("王壮");
        students.add("徐福记");
    }


    public boolean isStudent(String name) {
        return students.contains(name);
    }


    public static void main(String[] args) {
        new ImmutableDemo().isStudent("ABC");
    }
}
