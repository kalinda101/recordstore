package com.dream.constants;

/**
 * @author 匠人码农
 * @date 2020/11/25 8:06
 * 概要：
 *     常量定义类
 */

public class Constants {
    /*
       订单状态
     */
    //待支付
    public static final Integer ORDER_STATUS_PENDING_PAYMENT = 0;

    //待发货
    public static final Integer ORDER_STATUS_PENDING_SHIP = 1;

    //待收货
    public static final Integer ORDER_STATUS_PENDING_ARRIVE = 2;

    //已签收
    public static final Integer ORDER_STATUS_SIGNED = 3;

}
