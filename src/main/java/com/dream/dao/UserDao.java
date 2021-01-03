package com.dream.dao;

import com.dream.bean.User;

/**
 * @author 匠人码农
 * @date 2020/11/11 13:34
 * 概要：
 *    UserDao接口类
 */

public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param userName  用户名
     * @return          如果为null则用户不存在，否则结果为查询的用户信息。
     */
    public User queryUserByUserName(String userName);

    /**
     * 根据用户名和密码查询用户信息
     * @param userName  用户名
     * @param password  密码
     * @return          如果为null则用户名不存在或者密码错误，否则该用户存在。
     */
    public User queryUserByUserNameAndPassword(String userName, String password);


    /**
     * 保存用户信息
     * @param user  用户信息
     * @return      如果为-1,保存失败。否则保存成功。
     */
    public int saveUser(User user);

}
