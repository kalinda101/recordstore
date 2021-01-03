<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--EL表达式支持-->
<%@ page isELIgnored="false" %>
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
				<span class="wel_word"></span>
					<%@ include file="/pages/common/login_success_menu.jsp" %>
		</div>
		
		<div id="main">
		
			<h1>注册成功! &nbsp;&nbsp;&nbsp;<a href="index.jsp" style="color: #f5a200;text-decoration: none">转到主页</a></h1>
	
		</div>
		<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>