package com.yufeng.extend.interfaces.impl;

import com.yufeng.extend.interfaces.IChannel;
import com.yufeng.extend.interfaces.IMessage;


/**
 * @description
 *      1. 演示子类实现多个接口、子类对象向上转型
 *      2. 演示接口与Object类的转换
 *      3. 小结
 *          3-1 接口是无法继承Object的, 所以接口绝对不是Object的子类
 *          3-2 但是接口子类实例对象是Object的子类, 所以接口可以通过Object接收
 *          3-3 Object类对象可以接收所有数据类型, 包括基本数据类型、类对象、接口对象、数组
 * @author yufeng
 * @create 2020-02-16
 */
public class MessageImpl02 implements IMessage, IChannel {

    @Override
    public boolean connect() {
        System.out.println("消息发送通道建立");
        return true;
    }


    @Override
    public String getInfo() {
        if (connect()) {
            return "通道建立成功, 获得消息";
        }
        return "通道建立失败, 无法获取消息";
    }


    public static void main(String[] args) {
        IMessage message = new MessageImpl02();
        System.out.println(message.getInfo());

        /** 子类对象向上转型演示 */
        IChannel channel = (IChannel)message;
        System.out.println(channel.connect());          // 可以打印IChannel接口中的方法
        //System.out.println(channel.getInfo());        // 无法打印IMessage接口中的方法

        /** 接口与Object类的转换 */
        IMessage message02 = new MessageImpl02();
        Object obj = message02;
        IChannel channel02 = (IChannel)obj;
        System.out.println(channel02.connect());
    }
}
