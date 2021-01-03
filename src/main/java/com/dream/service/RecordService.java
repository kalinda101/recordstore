package com.dream.service;

import com.dream.bean.Record;
import com.dream.bean.Page;

import java.util.List;

/**
 * @author 农农
 * @date 2020/11/15 15:55
 * 概要：
 *    RecordService接口
 */

public interface RecordService {
    /**
     * 添加唱片
     * @param record 唱片信息
     */
    public void addRecord(Record record);

    /**
     * 通过ID删除唱片
     * @param id 唱片ID
     */
    public void deleteRecordById(Integer id);

    /**
     * 更新唱片信息
     * @param record 唱片信息
     */
    public void updateRecordById(Record record);

    /**
     * 通过Id查询唱片信息
     * @param id 唱片Id
     * @return 唱片信息
     */
    public Record queryRecordById(Integer id);

    /**
     * 查询所有唱片信息
     * @return 所有唱片信息List
     */
    public List<Record> queryRecords();

    /**
     * 实现分页
     * @param pageNo     当前页码
     * @param pageSize   每页容纳数据条数
     * @return
     */
    public Page<Record> page(int pageNo, int pageSize);

    /**
     * 通过价格区间实现分页
     * @param pageNo   当前页码
     * @param pageSize 每页最大件数
     * @param minPrice 最小价格
     * @param maxPrice 最大价格
     */
    public Page<Record> page(int pageNo, int pageSize, int minPrice, int maxPrice);

}
