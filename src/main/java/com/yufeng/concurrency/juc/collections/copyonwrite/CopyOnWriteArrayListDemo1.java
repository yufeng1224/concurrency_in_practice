package com.yufeng.concurrency.juc.collections.copyonwrite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description
 *      演示CopyOnWriteArrayList可以在迭代的过程中修改数组内容, 但是ArrayList不行(对比)
 * @author yufeng
 * @create 2020-03-19
 */
public class CopyOnWriteArrayListDemo1 {

    /**
     * 1. ArrayList无法在迭代的时候进行增删的操作, 否则会抛出异常
     * 2. 抛出的异常是: java.util.ConcurrentModificationException
     * 3. 原因: checkForComodification()进行了校验
     */
    public static void arrayListTest() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("list is: " + list);
            String next = iterator.next();
            System.out.println(next);

            /** 删除操作, 下一次迭代报错抛异常 */
//            if (next.equals("2")) {
//                list.remove("5");
//            }
            /** 新增操作, 下一次迭代报错抛异常 */
//            if (next.equals("3")) {
//                list.add("3 found");
//            }

            /** 支持修改操作 */
            if (next.equals("4")) {
                list.set(4, "55");
            }
        }
    }

    /**
     * 1. CopyOnWriteArrayList支持在迭代的时候进行增删的操作
     * 2. 后面两次迭代输出的内容
     *    list is[1, 2, 3, 4, 3 found]
     *    4
     *    list is[1, 2, 3, 4, 3 found]
     *    5
     * 3. 发现修改的数据没有反馈在迭代输出当中, 还是输出原有的数据
     * 4. 原因: 迭代的时候, 依然使用的是旧数组
     */
    public static void CopyOnWriteArrayListTest() {

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            System.out.println("list is" + list);
            String next = iterator.next();
            System.out.println(next);

            if (next.equals("2")) {
                list.remove("5");
            }
            if (next.equals("3")) {
                list.add("3 found");
            }
        }
    }

    public static void main(String[] args) {
        arrayListTest();
        CopyOnWriteArrayListTest();
    }
}
