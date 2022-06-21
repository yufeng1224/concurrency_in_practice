package com.yufeng.concurrency.jcip.part1.chapter03;

import com.yufeng.concurrency.jcip.annotations.Immutable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 * @description
 *      1. 在可变对象基础上构建不可变类
 *      2. stooges属性是私有的, 其他类无法修改set里面的数据
 * @author yufeng
 * @create 2020-04-25
 */
@Immutable
public class ThreeStooges {

    private final Set<String> stooges = new HashSet<>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }

    public String getStoogeNames() {
        List<String> stooges = new Vector<>();
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
        return stooges.toString();
    }

    public static void main(String[] args) {
        ThreeStooges threeStooges = new ThreeStooges();
        System.out.println(threeStooges.isStooge("yufeng"));

        threeStooges.stooges.add("yufeng");             // 只允许当前类修改
        System.out.println(threeStooges.isStooge("yufeng"));

        System.out.println(threeStooges.getStoogeNames());
    }

}
