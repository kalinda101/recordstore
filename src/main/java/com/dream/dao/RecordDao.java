package com.dream.dao;

import com.dream.bean.Record;

import java.util.List;


public interface RecordDao {

    /**
     * 添加唱片信息
     * @param record
     * @return
     */
    public int addRecord(Record record);

    /**
     * 通过id删除唱片信息
     * @param id
     * @return
     */
    public int deleteRecordById(Integer id);

    /**
     * 更新唱片信息
     * @param record
     * @return
     */
    public int updateRecordById(Record record);

    /**
     * 通过Id查询唱片信息
     * @param id
     * @return
     */
    public Record queryRecordById(Integer id);

    /**
     * 查询所有唱片信息
     * @return
     */
    public List<Record> queryRecords();

    /**
     * 查询t_record表有效的数据件数
     * @return 返回数据表的有效件数
     */
    public int queryForRecordTotal();

    /**
     * 通过价格区间查询图书有效件数
     * @param minPrice
     * @param maxPrice
     * @return
     */
    public int queryForRecordTotal(int minPrice, int maxPrice);

    /**
     * 查询当前页的数据
     * @param begin      开始数值
     * @param pageSize   每页容量
     * @return           当前页数据
     */
    public List<Record> queryForItems(Integer begin, int pageSize);

    /**
     * 通过价格区间查询书籍有效信息
     * @param begin
     * @param pageSize
     * @param minPrice
     * @param maxPrice
     * @return
     */
    public List<Record> queryForItems(int begin, int pageSize, int minPrice, int maxPrice);

}
