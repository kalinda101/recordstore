package com.dream.service;

import com.dream.bean.Cart;
import com.dream.bean.Order;
import com.dream.bean.OrderItem;

import java.util.List;

/**
 * @author 匠人码农
 * @date 2020/11/25 7:43
 * 概要：
 *    订单service接口
 */

public interface OrderService {
    /**
     * 生成订单
     *
     * @param cart   购物车
     * @param userId 用户ID
     */
    String createOrder(Cart cart, Integer userId);

    /**
     * 查看我的所有订单
     *
     * @param userId 用户ID
     * @return 返回查询用户的所有订单
     */
    List<Order> showMyOrders(Integer userId);

    /**
     * 通过订单号查询该订单的商品详情
     * @param orderId  订单号
     * @return         该订单的商品信息
     */
    List<OrderItem> showOrderDetail(String orderId);

    /**
     * 支付
     * @param orderId  订单号
     */
    void pay(String orderId);

    /**
     * 管理员查询所有订单
     * @return  所有订单
     */
    List<Order> showAllOrders();

    /**
     * 管理员发货
     * @param orderId  订单号
     */
    void sendOrder(String orderId);

    /**
     * 客户签收订单
     * @param orderId  订单号
     */
    void signOrder(String orderId);

}
