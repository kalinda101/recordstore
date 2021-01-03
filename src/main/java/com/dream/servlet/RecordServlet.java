package com.dream.servlet;

import com.dream.bean.Record;
import com.dream.bean.Page;
import com.dream.service.RecordService;
import com.dream.service.impl.RecordServiceImpl;
import com.dream.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class RecordServlet extends BaseServlet {

    //声明RecordService
    RecordService recordService = new RecordServiceImpl();

    /**
     * 添加唱片功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //唱片后显示到最后一页
        // 获取当前页码
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        // 当前页码 + 1
        pageNo+=1;

        //封装JavaBean
        Record record = (Record) WebUtils.copygetParameterMapToBean(req.getParameterMap(),new Record());

        //执行Service的添加唱片
        recordService.addRecord(record);

        //跳转到唱片列表页面
        ///manager/recordServlet?action=queryRecordList
        /**
         * 此处只能用重定向，不能用请求转发
         * 用请求转发，由于浏览器URL中保存了请求参数，当用户刷新画面的时候，还会执行添加的请求，相当于重复添加这张唱片。
         * 表单会重复提交
         */
//        req.getRequestDispatcher("/manager/recordServlet?action=queryRecordList").forward(req,resp);
        //没有分页功能
//        resp.sendRedirect(req.getContextPath() + "/manager/recordServlet?action=queryRecordList");
        //分页功能
        resp.sendRedirect(req.getContextPath() + "/manager/recordServlet?action=page&pageNo=" + pageNo);
    }

    /**
     * 修改唱片信息功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取pageNo
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);

        //封装JavaBean
        Record record = (Record) WebUtils.copygetParameterMapToBean(req.getParameterMap(),new Record());

        //调用service的更新处理
        recordService.updateRecordById(record);

        //跳转到列表一览画面(manager/recordServlet?action=queryRecordList)pages/manager/record_edit.jsp?
        //没有分页功能
//        resp.sendRedirect(req.getContextPath() + "/manager/recordServlet?action=queryRecordList");
        //含有分页功能
        resp.sendRedirect(req.getContextPath() + "/manager/recordServlet?action=page&pageNo=" + pageNo);

    }

    /**
     * 删除唱片功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取pageNo
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);

        //获取ID
        String id= req.getParameter("id");
        //调用service删除唱片功能
        recordService.deleteRecordById(Integer.parseInt(id));
        //响应重定向
        //没有分页
//        resp.sendRedirect(req.getContextPath() + "/manager/recordServlet?action=queryRecordList");
        //有分页 转发到删除页本页
        resp.sendRedirect(req.getContextPath() + "/manager/recordServlet?action=page&pageNo=" + pageNo);
    }

    /**
     * 查询指定ID的唱片信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void queryRecord(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取唱片ID值
        int id = WebUtils.parseInt(req.getParameter("id"),1);

        //执行查询操作
        Record record = recordService.queryRecordById(id);

        //回传数据
        req.setAttribute("record",record);

        //跳转到唱片编辑页面(/pages / manager / record_edit.jsp)pages/manager/record_edit.jsp?action=queryRecord&methord=update&id=${record.id}
        req.getRequestDispatcher("/pages/manager/record_edit.jsp").forward(req,resp);
    }

    /**
     * 查询所有唱片信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void queryRecordList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用service
        List<Record> list = recordService.queryRecords();
        //不为null
        if(list != null){
            //回传recordList
            req.setAttribute("recordList",list);
            //返回页面
            req.getRequestDispatcher("/pages/manager/record_manager.jsp").forward(req,resp);
        }
    }

    /**
     * 分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取pageNo和pageSize参数
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //2.调用Record service
        Page<Record> page = recordService.page(pageNo,pageSize);

        //3.设置分页条的url地址
        page.setUrl("manager/recordServlet?action=page");

        //4.将page对象保存到request域中，回传数据
        req.setAttribute("page",page);

        //5.请求转发，返回页面
        req.getRequestDispatcher("/pages/manager/record_manager.jsp").forward(req,resp);

    }



}
