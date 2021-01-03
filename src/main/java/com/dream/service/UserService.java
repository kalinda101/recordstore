package com.dream.service;

import com.dream.bean.User;

/**
 * @author 匠人码农
 * @date 2020/11/11 16:27
 * 概要：
 *    用户接口
 */

public interface UserService {


    /**
     * 注册用户功能
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 检查用户是否已经存在
     * @param userName
     * @return true:表示用户已经存在<br/>
     *         false:表示用户不存在
     *
     */
    public boolean existsUserName(String userName);

}
