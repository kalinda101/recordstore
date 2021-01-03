package com.dream.service;

import com.dream.bean.Cart;
import com.dream.bean.CartItem;
import com.dream.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author 匠人码农
 * @date 2020/11/25 10:18
 * 概要：
 *    创建订单service测试
 */

public class OrderServiceTest {

    OrderService orderService= new OrderServiceImpl();

    @Test
    public void createOrder() {
        Cart cart = new Cart();

        CartItem cartItem = new CartItem();
        cartItem.setGoodsId(1);
        cartItem.setGoodsName("vue.js从入门到精通");
        cartItem.setPrice(new BigDecimal(99.99));
        cartItem.setTotalPrice(new BigDecimal(99.99));
        cartItem.setGoodsCount(1);
        cart.addItem(cartItem);

        Integer userId = 1;
        orderService.createOrder(cart,userId);

    }
}