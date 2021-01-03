<%@ page import="com.dream.bean.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--EL表达式支持-->
<%@ page isELIgnored="false" %>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临唱片商城</span>

    <c:if test="${sessionScope.user.permission!=1}">
        <a href="orderServlet?action=showMyOrders">我的订单</a>
    </c:if>
    <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>
