package com.yufeng.extend.lambda;

/**
 * @description
 *      函数式接口
 * @author yufeng
 * @create 2020-02-15
 */
@FunctionalInterface
public interface MyFunction2<T, R> {

    public R getValue(T t1, T t2);

}
