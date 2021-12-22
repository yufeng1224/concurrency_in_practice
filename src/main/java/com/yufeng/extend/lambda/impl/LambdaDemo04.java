package com.yufeng.extend.lambda.impl;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java内置的四大核心函数式接口
 *     1. Consumer<T>: 消费型接口, void accept(T t);
 *     2. Supplier<T>: 供给型接口, T get();
 *     3. Function<T, R>: 函数型接口, R apply(T t);
 *     4. Predicate<T>: 断言型接口, boolean test(T t);
 *
 * @author yufeng
 * @create 2020-02-15
 */
public class LambdaDemo04 {

    /**
     * 消费性接口演示
     */
    @Test
    public void test1() {
        happy(1000, m -> System.out.println("消费" + m + "元玩游戏"));
    }

    public void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }


    /**
     * 供给型接口演示
     */
    @Test
    public void test2() {
        List<Integer> numList = getNumList(10, () -> (int)(Math.random() * 100));

        for (Integer num : numList) {
            System.out.println(num);
        }
    }

    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Integer n = supplier.get();
            list.add(n);
        }

        return list;
    }


    /**
     * 函数型接口
     */
    @Test
    public void test3() {
        String newStr = strHandler("\t\t\t 游戏真好玩!  ", str -> str.trim());
        System.out.println(newStr);

        String subStr = strHandler("游戏真好玩, 我还要充钱!", str -> str.substring(2));
        System.out.println(subStr);
    }

    public String strHandler(String string, Function<String, String> function) {
        return function.apply(string);
    }


    /**
     * 断言型接口
     */
    @Test
    public void test4() {
        List<String> list = Arrays.asList("Hello", "lambda", "www", "ok", "good");
        List<String> resList = filterStr(list, s -> s.length() > 3);

        for (String str : resList) {
            System.out.println(str);
        }
    }

    public List<String> filterStr(List<String> list, Predicate<String> pre) {
        List<String> strList = new ArrayList<>();

        for (String str : list) {
            if (pre.test(str)) {
                strList.add(str);
            }
        }
        return strList;
    }
}
