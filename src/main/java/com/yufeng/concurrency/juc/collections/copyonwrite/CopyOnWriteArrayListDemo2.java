package com.yufeng.concurrency.juc.collections.copyonwrite;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description
 *      对比两个迭代器
 * @author yufeng
 * @create 2020-03-19
 */
public class CopyOnWriteArrayListDemo2 {

    /**
     * 迭代时数据过期
     */
    public static void iteratorTest1() {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(new Integer[]{1, 2, 3});
        System.out.println(list);
        Iterator<Integer> itr1 = list.iterator();
        list.add(4);
        System.out.println(list);
        Iterator<Integer> itr2 = list.iterator();

        /** 1, 2, 3 */
        itr1.forEachRemaining(System.out::println);
        /** 1, 2, 3, 4 */
        itr2.forEachRemaining(System.out::println);
    }


    public static void main(String[] args) {
        iteratorTest1();
    }
}
