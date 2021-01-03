<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--EL表达式支持-->
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑唱片</title>
<%@ include file="/pages/common/head.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
<!--区分是修改唱片操作还是添加唱片操作？解决方案2：判断id的有无，有则为update，无则为add-->
<!--${empty param.id ? "add" : "update"}-->
<!--区分是修改唱片操作还是添加唱片操作？解决方案3：判断record的有无，有则为update，无则为add-->
<!--${empty requestScope.record ? "add" : "update"}-->

		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.jpg" >
			<span class="wel_word">编辑唱片</span>
			<%@ include file="/pages/common/manager_menu.jsp" %>
		</div>
		
		<div id="main">
			<form action="manager/recordServlet" methord="get">
			    <!--解决追加唱片后回到的画面更新到最后一页-->
			    <input type="hidden" name="pageNo" value="${param.pageNo}">
			    <!--区分是修改唱片操作还是添加唱片操作？解决方案：在这里获取record_manager.jsp请求时追加的methord请求参数。使用el表达式获取。-->
			    <input type="hidden" name="action" value="${param.methord}"/>
			    <!--修改操作时需要唱片id，从record_manager。jsp的请求中获取id值-->
			    <input type="hidden" name="id" value="${param.id}"/>
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="name" type="text" value="${requestScope.record.name}"/></td>
						<td><input name="price" type="text" value="${requestScope.record.price}"/></td>
						<td><input name="author" type="text" value="${requestScope.record.author}"/></td>
						<td><input name="sales" type="text" value="${requestScope.record.sales}"/></td>
						<td><input name="stock" type="text" value="${requestScope.record.stock}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>
		<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>