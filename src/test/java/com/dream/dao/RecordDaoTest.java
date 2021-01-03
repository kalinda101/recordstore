package com.dream.dao;

import com.dream.bean.Record;
import com.dream.dao.impl.RecordDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author 农农
 * @date 2020/12/29 8:53
 * 概要：
 *     RecordDao测试类
 */

public class RecordDaoTest {

    RecordDao RecordDao = new RecordDaoImpl();

    @Test
    public void addRecord() {
        Record Record = new Record();
        Record.setName("冬青");
        Record.setAuthor("戴祎");
        Record.setPrice(new BigDecimal(29.9));
        Record.setSales(10000);
        Record.setStock(200);
        Record.setDelFlg("0");
        if(RecordDao.addRecord(Record) != -1){
            System.out.println("插入数据成功");
        }else{
            System.out.println("插入数据失败");
        }

    }

    @Test
    public void deleteRecordById() {
//        Record Record = new Record();
//        Record.setId(11);
//        Record.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        if(RecordDao.deleteRecordById(12) != -1){
            System.out.println("删除数据成功");
        }else{
            System.out.println("删除数据失败");
        }
    }

    @Test
    public void updateRecord() {

        Record Record = new Record();
        Record.setId(11);
        Record.setName("冬青2");
        Record.setAuthor("戴祎2");
        Record.setPrice(new BigDecimal(39.9));
        Record.setSales(10009);
        Record.setStock(209);
        Record.setDelFlg("0");
        Record.setImgPath("static/img/0.jpg");
        Record.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        if(RecordDao.updateRecordById(Record) != -1){
            System.out.println("更新数据成功");
        }else{
            System.out.println("更新数据失败");
        }
    }

    @Test
    public void queryRecordById() {
        Record Record = RecordDao.queryRecordById(11);
        if( Record != null){

            System.out.println("查询数据成功");
            System.out.println("唱片信息如下:" + Record);
        }else{
            System.out.println("查询数据失败");
        }
    }

    @Test
    public void queryRecords() {
        List<Record> list = RecordDao.queryRecords();

        if( list !=null ) {
            System.out.println("查询唱片信息成功");
            list.forEach(new Consumer<Record>() {
                public void accept(Record Record) {
                    System.out.println(Record);
                }
            });
        }else{
            System.out.println("查询唱片信息失败");
        }
    }
}