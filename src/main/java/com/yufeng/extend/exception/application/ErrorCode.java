package com.yufeng.extend.exception.application;

/**
 * @description
 *      错误码接口
 * @author yufeng
 * @create 2020-02-18
 */
public interface ErrorCode {

    /**
     * 获取错误码
     */
    public String getCode();


    /**
     * 获取错误信息
     */
    public String getDescription();

}
