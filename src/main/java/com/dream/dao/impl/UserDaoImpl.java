package com.dream.dao.impl;

import com.dream.bean.User;
import com.dream.dao.UserDao;

/**
 * @author 匠人码农
 * @date 2020/11/11 13:43
 * 概要：
 *    UserDao接口的实现类
 */

public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User queryUserByUserName(String userName) {

        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ?";

        return queryForOne(User.class,sql,userName);

    }
    /*public int queryUserBy*/

    @Override
    public User queryUserByUserNameAndPassword(String userName, String password) {
        String sql = "select `id`,`username`,`password`,`email`,`permission` from t_user where username = ? and password = ?";

        return queryForOne(User.class,sql,userName,password);
    }


    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`,`registtime`) values(?,?,?,?)";

        return update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getRegistTime());
    }
}
