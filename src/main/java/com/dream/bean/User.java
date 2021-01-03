package com.dream.bean;

/**
 * @author 匠人码农
 * @date 2020/11/11 8:19
 * 概要：
 *     用户信息类
 */

public class User {
    //ID
    private Integer id;
    //用户名
    private String username;
    //密码
    private String password;
    //邮件地址
    private String email;
    //创建时间
    private String registTime;
    //更新时间
    private String updateTime;
    private int permission;
    //无参构造器
    public User() {

    }

    //有参数构造器


    public User(Integer id, String username, String password, String email, String registTime, String updateTime, int permission) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.registTime = registTime;
        this.updateTime = updateTime;
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", registTime='" + registTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", permission=" + permission +
                '}';
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public User(int permission) {
        this.permission = permission;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistTime() {
        return registTime;
    }

    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
