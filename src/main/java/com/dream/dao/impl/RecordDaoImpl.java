package com.dream.dao.impl;

import com.dream.bean.Record;
import com.dream.dao.RecordDao;

import java.util.List;

/**
 * @author 农农
 * @date 2020/12/29 8:08
 * 概要：
 *     唱片实现类
 */

public class RecordDaoImpl extends BaseDao implements RecordDao {

    /**
     * 添加唱片信息
     *
     * @param record
     * @return
     */
    @Override
    public int addRecord(Record record) {
        String sql = "insert into t_record(name,author,price,sales,stock,image_path) values (?,?,?,?,?,?)";
        return update(sql,record.getName(),record.getAuthor(),record.getPrice(),record.getSales(),record.getStock(),record.getImgPath());
    }

    /**
     * 通过id删除唱片信息
     *
     * @param id
     * @return
     */
    @Override
    public int deleteRecordById(Integer id) {
        String sql = "update t_record set delflg = '1',updatetime = now() where id = ?";
        return update(sql,id);
    }

    /**
     * 更新唱片信息
     *
     * @param record
     * @return
     */
    @Override
    public int updateRecordById(Record record) {
        String sql = "update t_record set name = ?,author = ?,price = ?,sales = ?,stock = ?,updatetime = now() where id = ?";
        return update(sql,record.getName(),record.getAuthor(),record.getPrice(),record.getSales(),record.getStock(),record.getId());
    }

    /**
     * 通过Id查询唱片信息
     *
     * @param id
     * @return
     */
    @Override
    public Record queryRecordById(Integer id) {
        String sql = "select id,name,author,price,sales,stock,image_path imgPath,delflg,createtime,updatetime from t_record where id = ?";
        return queryForOne(Record.class,sql,id);
    }

    /**
     * 查询所有唱片信息
     *
     * @return
     */
    @Override
    public List<Record> queryRecords() {
        String sql = "select id,name,author,price,sales,stock,image_path imgPath,delflg,createtime,updatetime from t_record where delflg <> '1'";
        return queryForList(Record.class,sql);
    }

    /**
     * 查询t_record表有效的数据件数
     *
     * @return 返回数据表的有效件数
     */
    @Override
    public int queryForRecordTotal() {
        String sql = "select count(1) from t_record where delflg = '0' ";
        return ((Long)queryForSingleValue(sql)).intValue();
    }

    /**
     * 通过价格区间查询唱片有效件数
     *
     * @param minPrice
     * @param maxPrice
     * @return
     */
    @Override
    public int queryForRecordTotal(int minPrice, int maxPrice) {
        String sql = "select count(1) from t_record where delflg = '0' and (price between ? and ?)";
        return ((Long)queryForSingleValue(sql,minPrice,maxPrice)).intValue();
    }

    /**
     * 查询当前页的数据
     * @param begin    开始数值
     * @param pageSize 每页容量
     * @return 当前页数据
     */
    @Override
    public List<Record> queryForItems(Integer begin, int pageSize) {
        String sql = "select id,name,author,price,sales,stock,image_path imgPath,delflg,createtime,updatetime from t_record where delflg <> '1' limit ?,?";
        return queryForList(Record.class,sql,begin,pageSize);
    }

    /**
     * 通过价格区间查询唱片有效信息并按照价格升序
     *
     * @param begin
     * @param pageSize
     * @param minPrice
     * @param maxPrice
     * @return
     */
    @Override
    public List<Record> queryForItems(int begin, int pageSize, int minPrice, int maxPrice) {
        String sql = "select id,name,author,price,sales,stock,image_path imgPath,delflg,createtime,updatetime from t_record where delflg <> '1' and (price between ? and ?) order by price limit ?,?";
        return queryForList(Record.class,sql,minPrice,maxPrice,begin,pageSize);
    }

}
