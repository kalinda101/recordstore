<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
<%@ include file="/pages/common/head.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}

</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.jpg" >
			<span class="wel_word">我的订单</span>
			<%@ include file="/pages/common/login_success_menu.jsp" %>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>
			<%--我的订单不为空场合--%>
			<c:if test="${not empty requestScope.orderList}">
                <c:forEach items="${requestScope.orderList}" var="order">
                    <tr>
                        <td>${fn:substring(order.createTime,0,10)}</td>
                        <td>${order.price}</td>
                        <td>
                            <c:choose>
                                <c:when test="${order.status == 0}">
                                   <span id="span"> <a   class="signClass" href="orderServlet?action=payOrder&orderId=${order.orderId}&orderStatus=${order.status}">待支付</a>
                                    </span> </c:when>
                                <c:when test="${order.status == 1}">
                                    待发货
                                </c:when>
                                <c:when test="${order.status == 2}">
                                    <a class="signClass" href="orderServlet?action=signOrder&orderId=${order.orderId}&orderStatus=${order.status}">待收货</a>
                                </c:when>
                                <c:when test="${order.status == 3}">
                                    已签收
                                </c:when>
                            </c:choose>
                        </td>
                        <td><a href="orderServlet?action=showOrderDetail&orderId=${order.orderId}&orderStatus=${order.status}">查看详情</a></td>
                    </tr>
                </c:forEach>
			</c:if>
			<c:if test="${empty requestScope.orderList}">
			    <tr>
                    <td colspan="4"><a href="index.jsp">当前没有订单信息，过去首页进行下单吧！</a></td>
                </tr>
			</c:if>

		</table>
		
	
	</div>

	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>
<script >
    $(function(){
        //给购物车的删除添加点击事件
        $("#span").click(function(){
            //获取商品名称 goodsName是在a标签的自定义属性
            return confirm("是否支付？");
        })

    })
</script>