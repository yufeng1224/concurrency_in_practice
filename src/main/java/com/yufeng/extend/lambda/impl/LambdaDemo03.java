package com.yufeng.extend.lambda.impl;


import com.yufeng.extend.lambda.MyFunction;
import com.yufeng.extend.lambda.MyFunction2;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * @description
 *      lambda代码演示(三)
 * @author yufeng
 * @create 2020-02-15
 */
public class LambdaDemo03 {

    List<Employee> employees = Arrays.asList(
            new Employee(101, "张三", 8, 3333),
            new Employee(102, "李四", 18, 6666),
            new Employee(103, "王五", 28, 7777),
            new Employee(104, "赵六", 38, 8888),
            new Employee(105, "田七", 48, 9999)
    );

    /**
     * Lambda练习1:
     *    调用Collection.sort()方法, 通过定制排序比较两个Employee(先按年龄比, 年龄相同按姓名比)
     */
    @Test
    public void test1() {
        Collections.sort(employees, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return -Integer.compare(e1.getAge(), e2.getAge());
            }
        });

        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    /**
     * Lambda练习2
     */
    @Test
    public void test2() {
        String trimStr = strHandler("\t\t\t yufeng   ", str -> str.trim());
        System.out.println(trimStr);

        String upperStr = strHandler("abcdef", str -> str.toUpperCase());
        System.out.println(upperStr);

        String subStr = strHandler("lambda exercise", str -> str.substring(2, 5));
        System.out.println(subStr);
    }

    /**
     * 字符串处理
     */
    public String strHandler(String str, MyFunction mf) {
        return mf.getValue(str);
    }

    /**
     * Lambda练习3
     */
    @Test
    public void test3() {
        operate(100L, 200L, (x, y) -> x + y);
        operate(100L, 200l, (x, y) -> x * y);
    }

    /**
     * 两个long型数据进行处理
     */
    public void operate(Long l1, Long l2, MyFunction2<Long, Long> mf) {
        System.out.println(mf.getValue(l1, l2));
    }

}
