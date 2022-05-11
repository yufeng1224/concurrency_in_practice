package com.yufeng.extend.interfaces;

/**
 * @description
 *      1. 定义一个接口
 *      2. 由于类名称和接口名称的定义相同, 所以为了区分, 此处接口名称加了字母I(interface的简写)
 *          (但是查看了一下spring的源码和成熟的生产项目代码, 发现没有加字母I, 所以此种命名方式不采纳)
 *
 *      3. 接口无法直接产生实例化对象, 所以对于接口的使用原则如下:
 *          3-1 接口需要被子类实现(implements), 一个子类可以实现多个父接口;
 *          3-2 子类(非抽象类), 则一定要实现接口中的全部抽象方法;
 *          3-3 接口对象可以利用子类对象的向上转型进行实例化;
 *
 *      4. 接口描述的是一个公共的定义标准, 所以在接口之中所有的抽象方法的访问权限都为public;
 * @author yufeng
 * @create 2020-02-16
 */
public interface IMessage {

    public static final String INFO = "www.yufeng.com";     // 全局常量

    String getInfo();                       // 抽象方法
}
