package com.yufeng.extend.interfaces.impl;

/**
 * @description
 *      定义一个抽象类
 * @author yufeng
 * @create 2020-02-16
 */
public abstract class DatabaseAbstract {

    /**
     * 接口中的abstract可以省略, 但是抽象类中不允许省略
     */
    public abstract boolean getDatabaseConnection();

}
