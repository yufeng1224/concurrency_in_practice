package com.yufeng.extend.lambda;

/**
 * @description
 *      函数式接口
 * @author yufeng
 * @create 2020-02-15
 */
@FunctionalInterface
public interface MyPredicate<T> {

    boolean test(T t);

}
