package com.dream.bean;

import java.math.BigDecimal;

/**
 * @author 匠人码农
 * @date 2020/11/24 15:31
 * 概要：
 *     订单商品项
 */

public class OrderItem {

    //ID
    private Integer id;

    //商品名称
    private String goodsName;

    //商品件数
    private Integer goodsCount;

    //商品单价
    private BigDecimal price;

    //商品总价格
    private BigDecimal totalPrice;

    //订单号
    private String orderId;

    //默认构造器
    public OrderItem() {
    }

    //全参数构造器
    public OrderItem(Integer id, String goodsName, Integer goodsCount, BigDecimal price, BigDecimal totalPrice, String orderId) {
        this.id = id;
        this.goodsName = goodsName;
        this.goodsCount = goodsCount;
        this.price = price;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
    }

    //重写toString
    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", goodsCount=" + goodsCount +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", orderId='" + orderId + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
