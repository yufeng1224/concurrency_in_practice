package com.yufeng.extend.exception.application;

import org.apache.commons.lang.StringUtils;

/**
 * @description
 *      业务错误码
 * @author yufeng
 * @create 2020-02-18
 */
public enum BizErrorCodeEnum implements ErrorCode {

    /** 未知异常 */
    NO_SERVICE("404", "网络异常, 服务器熔断"),
    UNSPECIFIED("500", "网络异常，请稍后再试"),

    /** 通用异常 */
    REQUEST_ERROR("20001", "入参异常,请检查入参后再次调用"),
    PAGE_NUM_IS_NULL("20002","页码不能为空"),
    PAGE_SIZE_IS_NULL("20003","页数不能为空"),
    ID_IS_NULL("20004","ID不能为空"),
    SEARCH_IS_NULL("20005","搜索条件不能为空"),

    /** 短信相关 */
    SEND_MASSAGE_FAIL("30001","发送短消息失败"),
    SEND_MASSAGE_OFTEN("30002","操作发送短消息太频繁,请稍后再试"),
    MESSAGE_TEMPLATE_UNDEFINED("30003","短信模板未定义"),

    /** 支付相关 */
    CREATE_PAY_ORDER_FAIL("40001","创建订单支付失败"),
    UPDATE_PAY_ORDER_FAIL("40002","更新支付订单失败"),
    DEL_PAY_ORDER_FAIL("40003","更新支付订单失败"),
    PAY_ORDER_NO_EXISTS("40004","支付订单不存在"),
    REFUND_APPLY_NO_EXISTS("40005","退款申请不存在"),
    VERIFY_NOT_PASS("40006","验签"),
    RES_FAIL("40007","响应失败"),
    PAY_CHANNEL_IS_NULL("40008","支付渠道不能为空"),
    PAY_CHANNEL_PARAM_ERROR("40009","支付订单渠道参数错误");


    /** 错误码 */
    private final String code;

    /** 描述 */
    private final String description;

    BizErrorCodeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }


    /**
     * 根据编码查询枚举
     */
    public static BizErrorCodeEnum getByCode(String code) {
        for (BizErrorCodeEnum value : BizErrorCodeEnum.values()) {
            if (StringUtils.equals(code, value.getCode())) {
                return value;
            }
        }
        return UNSPECIFIED;
    }


    /**
     * 枚举是否包含此code
     */
    public static Boolean contains(String code){
        for (BizErrorCodeEnum value : BizErrorCodeEnum.values()) {
            if (StringUtils.equals(code, value.getCode())) {
                return true;
            }
        }
        return  false;
    }


    @Override
    public String getCode() {
        return code;
    }


    @Override
    public String getDescription() {
        return description;
    }

}
