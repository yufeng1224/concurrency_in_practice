package com.yufeng.concurrency.juc.collections.copyonwrite;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @description
 *      ArrayList 删除元素相关演示: 在迭代时, 不能使用list进行删除, 可以使用iterator删除
 * @author yufeng
 * @create 2020-03-27
 */
public class ArrayListDemo {

    private ArrayList<String> list;
    {
        list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("2");
    }

    private ArrayList<String> getList() {
        return new ArrayList<>(list);
    }

    /**
     * 1. 删除集合中的'2', 此种方式只能删除相邻的第一个'2'
     * 2. 原因: 执行删除方法时, 会调用System.arraycopy方法, 导致删除元素时涉及到数组元素的移动, 所以相邻
     *    的一个就会被忽略掉
     * 3. 解决方案: 删除后, 将指针向前移以为
     */
    private void listDeleteForTest() {
        ArrayList<String> list = getList();
        System.out.println("listDeleteForTest 删除前: " + list);
        for (int i = 0; i < list.size(); i ++) {
            if ("2".equals(list.get(i))) {
                list.remove(i);
                //list.remove(i --);                  // 解决方案
            }
        }
        System.out.println("listDeleteForTest 删除后: " + list);
    }

    /**
     * 1. foreach写法实际上是对Iterable、hasNext、next方法的简写
     * 2. 原因: ArrayList中的内部类进行了校验, 检测到expectedModCount 和 modCount 不相等,
     *    然后抛出 ConcurrentModificationException
     * 3. 解决方案: 使用迭代器进行删除, 见 listDeleteIteratorTest() 函数
     */
    private void listDeleteForEachTest() {
        ArrayList<String> list = getList();
        for (String str : list) {
            System.out.println("list is: " + list);
            if ("2".equals(str)) {
                list.remove("2");
            }
        }
    }

    /**
     * 迭代时删除正确方式
     */
    private void listDeleteIteratorTest() {
        ArrayList<String> list = getList();
        System.out.println("listDeleteIteratorTest 删除前: " + list);
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            if("2".equals(iterator.next())){
                iterator.remove();                      // 使用迭代器删除
                //list.remove("2");                     // 在迭代器中用list会报错
            }
        }
        System.out.println("listDeleteIteratorTest 删除后: " + list);

    }

    public static void main(String[] args) {
        ArrayListDemo test = new ArrayListDemo();

        test.listDeleteForTest();
        test.listDeleteIteratorTest();
        test.listDeleteForEachTest();
    }

}
