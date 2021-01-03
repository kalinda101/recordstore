package com.dream.servlet;

import com.dream.bean.Cart;
import com.dream.bean.Order;
import com.dream.bean.OrderItem;
import com.dream.bean.User;
import com.dream.service.OrderService;
import com.dream.service.impl.OrderServiceImpl;
import com.dream.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet{

    //service声明
    OrderService orderService = new OrderServiceImpl();

    /**
     * 创建订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取参数
        //获取购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        //获取用户信息
        User user = (User) req.getSession().getAttribute("user");

        if(user == null){
            //跳转到的登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            //转发和重定向后不需要执行任何其他操作
            return;
        }

        //调用service
        if(cart != null){

            String orderId = orderService.createOrder(cart, user.getId());

            //回传数据
            req.getSession().setAttribute("orderId",orderId);

            //支付完的东西清空购物车
            req.getSession().removeAttribute("cart");

            //返回页面
            //            //这个地方要使用重定向 否则在用户刷新的时候又会生成一个订单
            //            //req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);
            resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
        }

    }

    /**
     * 查看我的所有订单信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取参数 用户ID
        User user = (User) req.getSession().getAttribute("user");

        //登录的情况
        if(user != null) {
            //查看
            List<Order> orderList = orderService.showMyOrders(user.getId());

            //回传数据
            req.setAttribute("orderList", orderList);

            //返回页面
            req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);


        }else{
            //未登录的情况下
            //返回页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }

    }

    /**
     * 查看该订单详情
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取参数 订单号
        String orderId = req.getParameter("orderId");

        //查看
        List<OrderItem> orderItemList = orderService.showOrderDetail(orderId);

        //回传数据
        req.setAttribute("orderItemList", orderItemList);
        //设置订单状态
        req.setAttribute("orderStatus",  req.getParameter("orderStatus"));
        //返回页面
        req.getRequestDispatcher("/pages/order/order_detail.jsp").forward(req, resp);


    }

    /**
     * 管理员查看所有订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //查看
        List<Order> allOrder = orderService.showAllOrders();

        //回传数据
        req.setAttribute("allOrder", allOrder);

        //返回页面
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);

    }

    /**
     * 发货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取订单号
        String orderId = req.getParameter("orderId");

        //发货
        orderService.sendOrder(orderId);

        //查新最新数据
        List<Order> allOrder = orderService.showAllOrders();

        //回传数据
        req.setAttribute("allOrder", allOrder);

        //返回页面
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);

    }

    protected void payOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取订单号
        String orderId = req.getParameter("orderId");

        //支付
        orderService.pay(orderId);

       //查看
       List<OrderItem> orderItemList = orderService.showOrderDetail(orderId);

       //查新最新数据
       List<Order> allOrder = orderService.showAllOrders();

       //回传数据
       req.setAttribute("orderItemList", orderItemList);

       //返回页面
       req.getRequestDispatcher("/pages/order/order_detail.jsp").forward(req, resp);

    }

    /**
     * 签收订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void signOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取订单号
        String orderId = req.getParameter("orderId");

        //获取用户
        User user= (User) req.getSession().getAttribute("user");

        //签收订单
        orderService.signOrder(orderId);

        //查看
        List<Order> orderList = orderService.showMyOrders(user.getId());

        //回传数据
        req.setAttribute("orderList", orderList);

        //返回页面
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);

    }





}
