<%@ page import="com.dream.bean.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>唱片商城会员注册页面</title>
<%@ include file="/pages/common/head.jsp" %>

<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="static/img/logo.jpg" >
				<%@ include file="/pages/common/login_success_menu.jsp" %>
		</div>
		
		<div id="main">
			<%
				User user=(User) request.getSession().getAttribute("user");
			%>
			<% if( user.getPermission()!=1){ %>
			<h1>欢迎回来 &nbsp;&nbsp;&nbsp;<a href="index.jsp" style="color: #f5a200;text-decoration: none">转到主页</a></h1>
			<% } else if( user.getPermission()==1) { %>
			<h1>欢迎回来 <a href="pages/manager/manager.jsp" style="color: #f5a200;text-decoration: none">后台管理</a></h1>
			<% } %>
		</div>
		
		<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>