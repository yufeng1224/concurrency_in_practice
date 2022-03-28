package com.yufeng.extend.innerclass.part2;

/**
 * @description
 *      内部类的作用: 实现信息隐藏
 *          1. 外部类不能使用private、 protected访问权限符来修饰, 而内部类则可以使用private和protected来修饰的
 *          2. 当我们使用private来修饰内部了的时候, 这个类就对外隐藏了
 *             2-1 当内部类实现某个接口的时候, 再进行向上转型。 对外部来说, 就完全隐藏了接口的实现
 * @author yufeng
 * @create 2020-03-09
 */
public class OuterClass {

    private class InnerClass implements InnerInterface {

        @Override
        public void innerMethod() {
            System.out.println("实现内部类隐藏");
        }
    }

    public InnerInterface getInner() {
        return new InnerClass();
    }

}
