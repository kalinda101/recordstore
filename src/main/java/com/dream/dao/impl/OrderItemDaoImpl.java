package com.dream.dao.impl;

import com.dream.bean.OrderItem;
import com.dream.dao.OrderItemDao;

import java.util.List;

/**
 * @author 匠人码农
 * @date 2020/11/24 15:46
 * 概要：
 *     订单项Dao实现类
 */

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    /**
     * 保存订单项信息
     *
     * @param orderItem 订单项信息
     * @return
     */
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_orderItem(goodsname, goodscount, price, totalprice, orderid) values(?,?,?,?,?)";
        return update(sql,orderItem.getGoodsName(),orderItem.getGoodsCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    /**
     * 通过订单号查询订单的详细商品信息
     *
     * @param orderId 订单号
     * @return 订单的商品信息
     */
    @Override
    public List<OrderItem> queryOderItemByOrderId(String orderId) {
        String sql ="select id, goodsname, goodscount, price, totalprice, orderid from t_orderItem where orderid = ? ";
        return queryForList(OrderItem.class,sql,orderId);
    }
}
