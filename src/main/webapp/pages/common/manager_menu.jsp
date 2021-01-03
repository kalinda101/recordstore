<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--EL表达式支持-->
<%@ page isELIgnored="false" %>
<div>
    <!--没有分页的情况下-->
    <!--<a href="manager/recordServlet?action=queryRecordList">唱片管理</a>-->
    <!--分页的情况-->
    <a href="manager/recordServlet?action=page" style="color: #f5a200">唱片管理</a>
    <a href="orderServlet?action=showAllOrders" style="color: #f5a200">订单管理</a>
    <a href="index.jsp" style="color: #f5a200">返回商城</a>
</div>
