package com.yufeng.extend.innerclass.part2;

/**
 * @description
 *     java是不允许继承多个类的, 内部类可以使我们的类去继承多个具体类或抽象类
 * @author yufeng
 * @create 2020-03-09
 */
public class MainExample {

    /**
     * 内部类1继承 ExampleOne
     */
    private class InnerOne extends ExampleOne {

        @Override
        public String name() {
            return super.name();
        }
    }

    /**
     * 内部类2继承 ExampleTwo
     */
    private class InnerTwo extends ExampleTwo {

        @Override
        public int age() {
            return super.age();
        }
    }

    public String name() {
        return new InnerOne().name();
    }

    public int age() {
        return new InnerTwo().age();
    }

    public static void main(String[] args) {
        MainExample main = new MainExample();

        System.out.println("姓名: " + main.name());
        System.out.println("年龄: " + main.age());
    }

}
