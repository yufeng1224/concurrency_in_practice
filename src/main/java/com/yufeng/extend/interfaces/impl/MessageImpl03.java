package com.yufeng.extend.interfaces.impl;

import com.yufeng.extend.interfaces.IChannel;
import com.yufeng.extend.interfaces.IMessage;

/**
 * @description
 *      演示子类继承抽象类并实现接口
 * @author yufeng
 * @create 2020-02-16
 */
public class MessageImpl03 extends DatabaseAbstract implements IMessage, IChannel {

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

    @Override
    public boolean getDatabaseConnection() {
        return true;
    }

    public static void main(String[] args) {
        IMessage message = new MessageImpl03();
        System.out.println(message.getInfo());
    }
}
