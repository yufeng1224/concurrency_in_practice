package com.yufeng.extend.innerclass.part2;

/**
 * @description
 *      测试
 * @author yufeng
 * @create 2020-03-09
 */
public class OuterClassTest {

    /**
     * 从这段代码里面我们只能知道OuterClass的getInner()方法能返回一个InnerInterface接口实例,
     * 但是并知道这个实例是怎么实现的
     */
    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        InnerInterface inner = outerClass.getInner();
        inner.innerMethod();
    }
}
