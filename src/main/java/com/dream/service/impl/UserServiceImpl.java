package com.dream.service.impl;

import com.dream.bean.User;
import com.dream.dao.UserDao;
import com.dream.dao.impl.UserDaoImpl;
import com.dream.service.UserService;

/**
 * @author 匠人码农
 * @date 2020/11/11 16:33
 * 概要：
 *     业务处理service
 */

public class UserServiceImpl implements UserService {

    //UserDao
    UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUserNameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUserName(String userName) {

        if(null != userDao.queryUserByUserName(userName)){
            return true;
        }
        return false;
    }
}
