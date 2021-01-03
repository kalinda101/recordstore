package com.dream.servlet;

import com.dream.bean.User;
import com.dream.service.UserService;
import com.dream.service.impl.UserServiceImpl;
import com.dream.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *     用户模块
 *         包含的功能：用户注册，用户登录功能
 *
 */

public class UserServlet extends BaseServlet {
    public static final String KAPTCHA_SESSION_KEY = "KAPTCHA_SESSION_KEY";
    UserService userService = new UserServiceImpl();

    /**
     * 检查用户是否存在
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取用户名
        String userName = req.getParameter("userName");

        //调用服务层
        boolean existsUserName = userService.existsUserName(userName);

        //创建存储结果的Map
        Map<String,Object> resultMap = new HashMap<String,Object>();

        //存放结果
        resultMap.put("existsUserName",existsUserName);

        //JSon转换
        Gson gson = new Gson();

        //转换成字符串
        String json = gson.toJson(resultMap);

        //输出字符串
        resp.getWriter().write(json);

    }

    /**
     * 用户登录功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //根据name和password获取permission


        //使用BeanUtil进行封装
        User user = (User)WebUtils.copygetParameterMapToBean(req.getParameterMap(),new User());

        //调用service
        User dbUser = userService.login(user);
        //用户和密码正确
        if(null != dbUser){
            System.out.println(dbUser);
            System.out.println(dbUser.getPermission());
            //user信息
            //登录成功的信息存放到session
            req.getSession().setAttribute("user",dbUser);

            //购物车不为空
            if(req.getSession().getAttribute("cart") != null){
                //跳转到购物车页面
                req.getRequestDispatcher("/pages/cart/cart.jsp").forward(req, resp);
            }else {
                //跳转到登录成功页面
                req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
            }
        }else{
            //回传信息
            req.setAttribute("msg","用户名或者密码错误！");
            req.setAttribute("username",user.getUsername());

            //跳转到登录页面
            req.getRequestDispatcher("pages/user/login.jsp").forward(req,resp);
        }
    }

    /**
     * 注册用户功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取session中谷歌生成的验证码
        String googleCode = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);

        //2.删除session中的验证码(防止表单重复提交)
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //3.获取输出的验证码
        //获取验证码
        String code = req.getParameter("code");

        //使用beanUtils进行JavaBean的封装
        User user = (User)WebUtils.copygetParameterMapToBean(req.getParameterMap(),new User());

        //4.检验验证码
        if(googleCode != null && !googleCode.equalsIgnoreCase(code)){
            //错误信息以及要回显的信息
            //错误信息
            req.setAttribute("msg","验证码错误！");
            //回显用户名
            req.setAttribute("username",user.getUsername());
            //回显邮件地址
            req.setAttribute("email",user.getEmail());

            //跳转回登录界面
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        } else {

            //bean设定
            user.setRegistTime(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(new Date()));

            //检查用户名是否存在
            //已经存在
            if(userService.existsUserName(user.getUsername())){
                //错误信息以及要回显的信息
                //错误信息
                req.setAttribute("msg","用户名已存在！");
                //回显用户名
                req.setAttribute("username",user.getUsername());
                //回显邮件地址
                req.setAttribute("email",user.getEmail());

                //跳转回登录页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
                //不存在
            } else {
                //跳转到成功页面
                userService.registUser(user);

                //把注册成功的用户信息存放到session中
                req.getSession().setAttribute("user",user);

                //请求转发到注册成功页面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }

    }

    /**
     * 用户注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //销毁session
        req.getSession().invalidate();

        //跳转到登录页面
//        req.getRequestDispatcher("pages/user/login.jsp").forward(req,resp);
        resp.sendRedirect(req.getContextPath());

    }
}
