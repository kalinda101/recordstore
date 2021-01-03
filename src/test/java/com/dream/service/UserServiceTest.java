package com.dream.service;

import com.dream.bean.User;
import com.dream.service.impl.UserServiceImpl;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author 匠人码农
 * @date 2020/11/11 16:43
 * 概要：
 *     用户service接口测试
 */

public class UserServiceTest {

    /**
     * 注册用户测试
     */
    @Test
    public void registUser() {
        UserService userService = new UserServiceImpl();

        User user = new User();
        user.setUsername("gust1");
        user.setPassword("gust1");
        user.setEmail("gust1@test.com");

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

        user.setRegistTime(dateFormat.format(date.getTime()));

        userService.registUser(user);


    }

    /**
     * 登录功能测试
     */
    @Test
    public void login() {
        UserService userService = new UserServiceImpl();

        User user = new User();
        user.setUsername("gust1");
        user.setPassword("gust1");
        user.setEmail("gust1@test.com");

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

        user.setRegistTime(dateFormat.format(date.getTime()));

        System.out.println(userService.login(user));
    }

    /**
     * 用户存在check
     */
    @Test
    public void existsUserName() {

        UserService userService = new UserServiceImpl();

        if(userService.existsUserName("gust")){
            System.out.println("用户已经存在！");
        }else{
            System.out.println("用户不存在！");
        }

    }
}