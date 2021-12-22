package com.yufeng.extend.lambda.impl;

import com.yufeng.extend.lambda.MyFun;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * 一、 Lambda 表达式的基础语法
 *     Java8中引入了一个新的操作符 "->", 该操作符称为箭头操作符或Lambda操作符,
 *     箭头操作符将Lambda表达式拆分为两部分:
 *     左侧: Lambda表达式的参数列表;
 *     右侧: Lambda表达式所需执行的功能, 即Lambda体;
 *
 * 二、 语法格式
 *     1. 无参数, 无返回值
 *        () -> System.out.println("Hello Lambda!");
 *
 *     2. 有一个参数, 并且无返回值
 *        (x) -> System.out.println(x)
 *        2-1 若只有一个参数, 小括号可以省略不写
 *            x -> System.out.println(x);
 *
 *     3. 有两个以上的参数, 有返回值, 并且Lambda体有多条语句
 *        Comparator<Integer> com = (x, y) -> {
 *            System.out.println("函数式接口");
 *            return Integer.compare(x, y);
 *        }
 *
 *     4. 若lambda体中只有一条语句, return和大括号都可以省略不写
 *        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
 *
 *     5. Lambda表达式的参数列表的数据类型可以省略不写, 因为JVM编译器可以通过上下文推断出数据类型, 即"类型推断"
 *        (Integer x, Integer y) -> Integer.compare(x, y)
 *
 *
 * 上联: 左右遇一括号省
 * 下联: 左侧推断类型省
 * 横批: 能省则省
 *
 * 三、 Lambda表达式需要"函数式接口"的支持
 *     函数式接口: 接口中只有一个抽象方法的接口, 称为函数式接口。 可以使用注解@FunctionalInterface修饰
 *     可以检查是否是函数式接口
 *
 * @author yufeng
 * @create 2020-02-15
 */
public class LambdaDemo02 {


    /**
     * Lambda语法格式1: 无参数、无返回值
     */
    @Test
    public void test1() throws Exception {
        int num = 0;                        //jdk1.7前，final关键字必须写

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!" + num);
            }
        };
        new Thread(runnable).start();

        System.out.println("-------------------------------");

        Runnable runnable1 = () -> System.out.println("Hello Lambda!" + num);
        new Thread(runnable1).start();

        TimeUnit.SECONDS.sleep(1);
    }


    /**
     * Lambda语法格式2: 有一个参数、无返回值
     */
    @Test
    public void test2(){
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("用lambda表达式调用Consumer接口中的方法");
    }


    /**
     * Lambda语法格式3: 有两个参数, 并且有返回值
     */
    @Test
    public void test3(){
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
        int big = comparator.compare(4,5);
        System.out.println(big);
    }


    /**
     * Lambda语法格式4: 有两个参数, 并且只有一个返回语句。(可以进行省略)
     */
    @Test
    public void test4(){
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
    }


    /**
     * 类型推断演示
     */
    @Test
    public void test5() {
        String[] str = {"aaa", "bbb", "ccc"};

        // String[] str;
        // str = {"aaa", "bbb", "ccc"};

        List<String> list = new ArrayList<>();
        show(new HashMap<>());
    }


    public void show(Map<String, Integer> map) {}


    /**
     * 练习: 对一个数进行运算
     */
    @Test
    public void test6() {
        Integer num = operation(100, x -> x * x);
        System.out.println(num);

        System.out.println(operation(200, y -> y + 200));
    }


    public Integer operation(Integer num, MyFun mf) {
        return mf.getValue(num);
    }
}
