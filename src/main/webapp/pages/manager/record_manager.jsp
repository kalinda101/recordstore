<%--suppress Annotator --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!--EL表达式支持-->
    <%@ page isELIgnored="false" %>
    <!-- JSTL 核心标签库 -->
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>唱片管理</title>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        //页面加载之后
        $(function(){
            //使用Jquery查找删除的a标签
            $("a.deleteClass").click(function(){
                 //获取要删除的唱片名称
                return confirm("确定要删除【" + $(this).parent().parent().find("td:first").text() + "】吗");
            })

            //分页确认按钮绑定单击事件
            $("#btnPage").click(function(){
                //获取控件的值
                var pageNo = $("#pageNo").val();
                var pageSize = $("#pageSize").val();

                //basePath从pageContext域中取出
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo + "&pageSize=" + pageSize;

            })
        });
    </script>
    </head>
    <body>

        <div id="header">
                <img class="logo_img" alt="" src="static/img/logo.jpg" >
                <span class="wel_word">唱片管理系统</span>
                <%@ include file="/pages/common/manager_menu.jsp" %>
        </div>

        <div id="main">
            <table>
                <tr>
                    <td>名称</td>
                    <td>价格</td>
                    <td>作者</td>
                    <td>销量</td>
                    <td>库存</td>
                    <td colspan="2">操作</td>
                </tr>
                <!-- 使用JSTL进行唱片信息的遍历
                       recordList的名称要和RecordServlet的setAttribute的属性名称一致
                -->
                <!--没有分页
                <c:forEach items="${requestScope.recordList}" var="record">
                    <tr>
                        <td>${record.name}</td>
                        <td>${record.price}</td>
                        <td>${record.author}</td>
                        <td>${record.sales}</td>
                        <td>${record.stock}</td>
                        <!--区分是修改唱片操作还是添加唱片操作？解决方案1：在这里追加methord请求参数-->
                        <td><a href="manager/recordServlet?action=queryRecord&methord=update&id=${record.id}">修改</a></td>
                        <td><a class="deleteClass" href="manager/recordServlet?action=delete&id=${record.id}">删除</a></td>
                    </tr>
                </c:forEach>
                -->
                <!--
                    分页实现
                -->
                <c:forEach items="${requestScope.page.items}" var="record">
                                <tr>
                                    <td>${record.name}</td>
                                    <td>${record.price}</td>
                                    <td>${record.author}</td>
                                    <td>${record.sales}</td>
                                    <td>${record.stock}</td>
                                    <!--区分是修改唱片操作还是添加唱片操作？解决方案1：在这里追加methord请求参数-->
                                    <td><a href="manager/recordServlet?action=queryRecord&methord=update&id=${record.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
                                    <td><a class="deleteClass" href="manager/recordServlet?action=delete&id=${record.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
                                </tr>
                            </c:forEach>

                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <!--区分是修改唱片操作还是添加唱片操作？解决方案1：在这里追加methord请求参数-->
                    <td><a href="pages/manager/record_edit.jsp?methord=add&pageNo=${requestScope.page.pageTotal}">添加唱片</a></td>
                </tr>
            </table>

            <!--分页静态包含-->
            <%@ include file="/pages/common/page_nav.jsp" %>

        </div>
        <%@ include file="/pages/common/footer.jsp" %>
    </body>
    </html>