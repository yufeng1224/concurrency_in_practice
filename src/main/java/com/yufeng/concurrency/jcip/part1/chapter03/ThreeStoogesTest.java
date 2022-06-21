package com.yufeng.concurrency.jcip.part1.chapter03;

/**
 * @description
 *      ThreeStooges 测试类
 * @author yufeng
 * @create 2020-04-25
 */
public class ThreeStoogesTest {

    public static void main(String[] args) {
        ThreeStooges threeStooges = new ThreeStooges();
        System.out.println(threeStooges.isStooge("yufeng"));

        //threeStooges.stooges.add("yufeng");
        //System.out.println(threeStooges.isStooge("yufeng"));

        System.out.println(threeStooges.getStoogeNames());
    }

}
