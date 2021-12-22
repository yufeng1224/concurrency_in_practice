package com.yufeng.extend.interfaces.impl;

import com.yufeng.extend.interfaces.IService;

/**
 * @description
 *      IService接口继承了IMessage和IChannel接口
 * @author yufeng
 * @create 2020-02-16
 */
public class MessageImpl04 implements IService {

    @Override
    public String service() {
        return "获取消息服务";
    }

    @Override
    public boolean connect() {
        return false;
    }

    @Override
    public String getInfo() {
        return null;
    }

    public static void main(String[] args) {
        IService service = new MessageImpl04();
        System.out.println(service.service());
    }

}
