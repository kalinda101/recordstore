package com.dream.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author
 * @date 2020/12/30 21:01
 */

public class Record {
    //ID
    private Integer id;
    //名称
    private String name;
    //作者
    private String author;
    //价格
    private BigDecimal price;
    //销量
    private Integer sales;
    //库存
    private Integer stock;
    //图片地址
    private String imgPath = "static/img/1.jpg";
    //删除FLG
    private String delFlg;
    //创建时间
    private Timestamp createTime;
    //更新时间
    private Timestamp updateTime;

    //重写toString方法
    @Override
    public String toString() {
        return new StringBuilder()
                .append("id:" + id + " ")
                .append("name:" + name + " ")
                .append("author:" +author + " ")
                .append("price:" + price + " ")
                .append("sales:" + sales + " ")
                .append("stock:" + stock + " ")
                .append("imgPath:" + imgPath +" ")
                .append("delFlg:" + delFlg +" ")
                .append("createTime:" + createTime +" ")
                .append("updateTime:" + updateTime +" ")
                .toString();
    }

    //默认构造器
    public Record() {

    }

    //全参数构造器
    public Record(Integer id, String name, String author, BigDecimal price, Integer sales, Integer stock, String imgPath, String delFlg, Timestamp createTime, Timestamp updateTime) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        //设置默认图片地址
        if(imgPath != null) {
            this.imgPath = imgPath;
        }
        this.delFlg = delFlg;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        if(imgPath != null) {
            this.imgPath = imgPath;
        }
    }

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

}
