package com.dream.service.impl;

import com.dream.bean.Record;
import com.dream.bean.Page;
import com.dream.dao.RecordDao;
import com.dream.dao.impl.RecordDaoImpl;
import com.dream.service.RecordService;

import java.util.List;

/**
 * @author 农农
 * @date 2020/12/30 16:05
 * 概要：
 *     RecordService实现类
 */

public class RecordServiceImpl implements RecordService {

    RecordDao recordDao = new RecordDaoImpl();

    /**
     * 添加唱片
     *
     * @param record 唱片信息
     */
    @Override
    public void addRecord(Record record) {
        recordDao.addRecord(record);
    }

    /**
     * 通过ID删除唱片
     *
     * @param id 唱片ID
     */
    @Override
    public void deleteRecordById(Integer id) {
        recordDao.deleteRecordById(id);
    }

    /**
     * 更新唱片信息
     *
     * @param record 唱片信息
     */
    @Override
    public void updateRecordById(Record record) {
        if(record.getId() != null) {
            recordDao.updateRecordById(record);
        }else{
            System.out.println("更新唱片信息的ID不能为NULL！");
        }
    }

    /**
     * 通过Id查询唱片信息
     *
     * @param id 唱片Id
     * @return 唱片信息
     */
    @Override
    public Record queryRecordById(Integer id) {
        return recordDao.queryRecordById(id);
    }

    /**
     * 查询所有唱片信息
     *
     * @return 所有唱片信息List
     */
    @Override
    public List<Record> queryRecords() {
        return recordDao.queryRecords();
    }

    /**
     * 实现分页
     *
     * @param pageNo   当前页码
     * @param pageSize 每页容纳数据条数
     * @return         返回分页信息对象
     */
    @Override
    public Page<Record> page(int pageNo, int pageSize) {
        //创建Page对象
        Page<Record> page = new Page<Record>();

        //总记录数
        int recordTotal = recordDao.queryForRecordTotal();
        //设置总记录数
        page.setRecordTotal(recordTotal);

        //求总页数
        int pageTotal = (recordTotal / pageSize);
        if(recordTotal % pageSize > 0){
            pageTotal += 1;
        }
        //设置总页数
        page.setPageTotal(pageTotal);

        //数据边界值验证
        if(pageNo < 1){
            pageNo = 1;
        }
        if(pageNo > pageTotal){
            pageNo = pageTotal;
        }

        //设置当前页码
        page.setPageNo(pageNo);

        //数据边界值验证
        if(pageSize < 1){
            pageSize = 1;
        }
        if(pageSize > recordTotal){
            pageSize = recordTotal;
        }
        //每页容量
        page.setPageSize(pageSize);

        //求当前页的数据
        int begin =(pageNo - 1) * pageSize;
        List<Record> items = recordDao.queryForItems(begin,pageSize);
        //设置前页的数据
        page.setItems(items);

        return page;
    }

    /**
     * 通过价格区间实现分页
     *
     * @param pageNo   当前页码
     * @param pageSize 每页最大件数
     * @param minPrice 最小价格
     * @param maxPrice 最大价格
     */
    @Override
    public Page<Record> page(int pageNo, int pageSize, int minPrice, int maxPrice) {
        //创建Page对象
        Page<Record> page = new Page<Record>();

        //总记录数
        int recordTotal = recordDao.queryForRecordTotal(minPrice,maxPrice);
        page.setRecordTotal(recordTotal);

        //总页数
        int pageTotal = (recordTotal / pageSize);
        if(recordTotal % pageSize > 0){
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);

        //数据边界值验证
        if(pageNo < 1){
            pageNo = 1;
        }
        if(pageNo > pageTotal){
            pageNo = pageTotal;
        }

        //当前页码
        page.setPageNo(pageNo);

        //数据边界值验证
        if(pageSize < 1){
            pageSize = 1;
        }
        if(pageSize > recordTotal){
            pageSize = recordTotal;
        }
        //每页容量
        page.setPageSize(pageSize);

        //当前记录数据
        int begin =(pageNo - 1) * pageSize;
        List<Record> items = recordDao.queryForItems(begin,pageSize,minPrice,maxPrice);
        page.setItems(items);

        return page;
    }
}
