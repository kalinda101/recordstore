package com.dream.servlet;

import com.dream.bean.Record;
import com.dream.bean.Cart;
import com.dream.bean.CartItem;
import com.dream.service.RecordService;
import com.dream.service.impl.RecordServiceImpl;
import com.dream.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class CartServlet extends BaseServlet {

    RecordService recordService = new RecordServiceImpl();

    /**
     * 实现添加购物车的功能。
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求参数 商品id
        int goodsId = WebUtils.parseInt(req.getParameter("goodsId"),0);

        //通过调用recordService获取唱片信息
        Record record = recordService.queryRecordById(goodsId);

        //把唱片信息转换成cartItem信息
        CartItem cartItem = new CartItem(record.getId(),record.getName(),1,record.getPrice(), record.getPrice());

        //创建购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        //购物车不存
        if(cart == null){
            //创建新的购物车
            cart = new Cart();
            //把购物车存放到session
            req.getSession().setAttribute("cart",cart);
        }

        //添加到cart
        cart.addItem(cartItem);

        //把最新的商品名称放到session
        req.getSession().setAttribute("lastGoodsName",record.getName());

        //
        //重定向到首页
        //使用头信息的Referer来记录来源.这样返回的话，会返回原来的页面。
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 实现删除购物车商品的功能。
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求参数 商品id
        int goodsId = WebUtils.parseInt(req.getParameter("goodsId"),0);

        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        //删除该商品
        cart.deleteItem(goodsId);

        //把购物车存放到session
//        req.getSession().setAttribute("cart",cart);

        //重定向到首页
        //使用头信息的Referer来记录来源.这样返回的话，会返回原来的页面。
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 实现清空购物车商品的功能。
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if(cart != null) {
            //删除该商品
            cart.clear();

            //session无效
            req.getSession().invalidate();

        }

        //重定向到首页
        //使用头信息的Referer来记录来源.这样返回的话，会返回原来的页面。
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 实现清空购物车商品的功能。
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求的商品ID
        int goodsId = WebUtils.parseInt(req.getParameter("goodsId"),0);

        //获取请求的商品数量
        int goodsCount = WebUtils.parseInt(req.getParameter("goodsCount"),0);

        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");


        if(cart != null) {
            //修改商品数量
            cart.updateItem(goodsId,goodsCount);

        }

        //重定向到首页
        //使用头信息的Referer来记录来源.这样返回的话，会返回原来的页面。
        resp.sendRedirect(req.getHeader("Referer"));
//        resp.sendRedirect("pages/cart/cart.jsp");
    }

    /**
     * 实现添加购物车的功能。（使用Ajax）
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求参数
        int goodsId = WebUtils.parseInt(req.getParameter("goodsId"),0);

        //通过调用recordService获取唱片信息
        Record record = recordService.queryRecordById(goodsId);

        //把唱片信息转换成cartItem信息
        CartItem cartItem = new CartItem(record.getId(),record.getName(),1,record.getPrice(), record.getPrice());

        //创建购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        //购物车不存
        if(cart == null){

            //创建新的购物车
            cart = new Cart();
            //把购物车存放到session
            req.getSession().setAttribute("cart",cart);

        }

        //添加到cart
        cart.addItem(cartItem);

        //最后一个内容加入到session中
        req.getSession().setAttribute("lastGoodsName",record.getName());


        //存放给结果用的Map
        Map<String,Object> resultMap = new HashMap<String,Object>();

        //把结果放到map中
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastGoodsName",record.getName());

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        //返回给客户端
        resp.getWriter().write(json);


    }


}
