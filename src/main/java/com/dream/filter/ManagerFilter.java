package com.dream.filter;

import com.dream.bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 匠人码农
 * @date 2020/11/27 8:38
 * 概要：
 *    实现Manager
 */

public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("ManagerFilter初期化完成！");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //转换类型
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;

        //获取session的user
        User user = (User) httpServletRequest.getSession().getAttribute("user");

        //用户没有登录
        if(user == null){
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else{
            //登录已完成
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
