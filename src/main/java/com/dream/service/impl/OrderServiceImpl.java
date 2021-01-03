package com.dream.service.impl;

import com.dream.bean.*;
import com.dream.constants.Constants;
import com.dream.dao.RecordDao;
import com.dream.dao.OrderDao;
import com.dream.dao.OrderItemDao;
import com.dream.dao.impl.RecordDaoImpl;
import com.dream.dao.impl.OrderDaoImpl;
import com.dream.dao.impl.OrderItemDaoImpl;
import com.dream.service.OrderService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 农农
 * @date 2020/11/25 7:49
 * 概要：
 *     订单Service实现接口
 */

public class OrderServiceImpl  implements OrderService {

    //orderDao定义
    OrderDao orderDao = new OrderDaoImpl();

    //orderItemDao定义
    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    //recordDao
    RecordDao recordDao = new RecordDaoImpl();

    /**
     * 生成订单
     *
     * @param cart    购物车
     * @param userId  用户ID
     * @return        订单号
     */
    @Override
    public String createOrder(Cart cart,Integer userId) {

        //----------------------------------------------
        // 保存订单信息
        //----------------------------------------------
        //定义order
        Order order = new Order();

        //创建唯一的订单号
        String orderId = System.currentTimeMillis() + "" +userId;

        //订单号
        order.setOrderId(orderId);

        //设置事件格式
        SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        //订单创建时间
        order.setCreateTime(SimpleDateFormat.format(new Date()));

        //订单金额
        order.setPrice(cart.getTotalPrice());

        //订单状态
        order.setStatus(Constants.ORDER_STATUS_PENDING_PAYMENT);

        //用户ID
        order.setUserId(userId);

        //执行保存订单信息
        orderDao.saveOreder(order);

//        int i =12/0;  //事务管理测试用代码

        //----------------------------------------------
        // 保存订单项信息
        //----------------------------------------------
        //定义orderItem
        OrderItem orderItem = new OrderItem();

        //遍历购物车商品
        for(CartItem cartItem : cart.getItems().values()){
            //订单号
            orderItem.setOrderId(orderId);
            //商品名称
            orderItem.setGoodsName(cartItem.getGoodsName());
            //商品数量
            orderItem.setGoodsCount(cartItem.getGoodsCount());
            //单价
            orderItem.setPrice(cartItem.getPrice());
            //商品金额
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            //保存订单项信息
            orderItemDao.saveOrderItem(orderItem);

            //----------------------------------------------
            // 库存信息更新信息
            //----------------------------------------------

            Record record = recordDao.queryRecordById(cartItem.getGoodsId());

            record.setSales(record.getSales() + cartItem.getGoodsCount());
            record.setStock(record.getStock() - cartItem.getGoodsCount());
            recordDao.updateRecordById(record);

        }

        return orderId;

    }

    /**
     * 查看我的所有订单
     *
     * @param userId 用户ID
     * @return 返回查询用户的所有订单
     */
    @Override
    public List<Order> showMyOrders(Integer userId) {
        return orderDao.queryOrdersByUserId(userId);
    }

    /**
     * 通过订单号查询该订单的商品详情
     *
     * @param orderId 订单号
     * @return 该订单的商品信息
     */
    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOderItemByOrderId(orderId);
    }

    @Override
    public void pay(String orderId) {
        orderDao.updateStatus(orderId,Constants.ORDER_STATUS_PENDING_SHIP);
    }

    /**
     * 管理员查询所有订单
     *
     * @return 所有订单
     */
    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    /**
     * 管理员发货
     *
     * @param orderId 订单号
     */
    @Override
    public void sendOrder(String orderId) {
        orderDao.updateStatus(orderId,Constants.ORDER_STATUS_PENDING_ARRIVE);
    }

    /**
     * 客户签收订单
     *
     * @param orderId 订单号
     */
    @Override
    public void signOrder(String orderId) {
        orderDao.updateStatus(orderId,Constants.ORDER_STATUS_SIGNED);
    }
}
