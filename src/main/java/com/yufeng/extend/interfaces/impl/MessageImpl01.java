package com.yufeng.extend.interfaces.impl;

import com.yufeng.extend.interfaces.IMessage;

/**
 * @description
 *      定义接口子类
 * @author yufeng
 * @create 2020-02-16
 */
public class MessageImpl01 implements IMessage {

    @Override
    public String getInfo() {
        return "获得消息";
    }

    public static void main(String[] args) {
        IMessage message = new MessageImpl01();
        System.out.println(message.getInfo());
        System.out.println(IMessage.INFO);                  // 直接输出全局常量
    }
}
