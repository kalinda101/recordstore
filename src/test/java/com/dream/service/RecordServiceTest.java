package com.dream.service;

import com.dream.bean.Record;
import com.dream.service.impl.RecordServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 农农
 * @date 2020/12/30 16:10122-
 * 概要：
 *    RecordService接口测试
 */

public class RecordServiceTest {

    RecordService recordService = new RecordServiceImpl();

    @Test
    public void addRecord() {
        Record record = new Record();
        record.setName("北京欢迎你");
        record.setAuthor("刘欢");
        record.setPrice(new BigDecimal(39.9));
        record.setSales(200);
        record.setStock(100);
        record.setDelFlg("0");
        record.setImgPath("static/img/1.jpg");
        recordService.addRecord(record);
    }

    @Test
    public void deleteRecordById() {
        recordService.deleteRecordById(10);
    }

    @Test
    public void updateRecord() {
        Record record = new Record();
        record.setId(12);
        record.setName("北京欢迎你2？");
        record.setAuthor("刘欢");
        record.setPrice(new BigDecimal(49.9));
        record.setSales(2800);
        record.setStock(10);
        record.setDelFlg("0");
        record.setImgPath("static/img/1.jpg");
        recordService.updateRecordById(record);
    }

    @Test
    public void queryRecordById() {
        System.out.println(recordService.queryRecordById(12));
    }

    @Test
    public void queryRecords() {
        List<Record> list = recordService.queryRecords();
        //使用lambda表达式遍历List
        list.forEach(record -> {
            System.out.println(record);
        });
    }
}