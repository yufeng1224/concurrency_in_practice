package com.yufeng.extend.interfaces;


/**
 * @description
 *      接口可以通过extends继承多个父接口, 称为接口多继承
 * @author yufeng
 * @create 2020-02-16
 */
public interface IService extends IMessage, IChannel {

    String service();

}
