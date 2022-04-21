<%@ page import="com.bus.service.AdminServiceImp" %>
<%@ page import="com.bus.entity.Admin" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2021/8/12
  Time: 14:32
  To change this template use MyFile | Settings | MyFile Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../LoginQuery.jsp"%>
<html>
<head>
    <title>电子商务管理系统</title>
    <link rel="stylesheet" href="../../CSS/page.css">
</head>
<style>
    table {
        width: 85%;
        height: 85%;
        color: white;
        text-align: center;
        margin: 50px 10px 10px 10px;
    }
</style>
<%
    AdminServiceImp ad = new AdminServiceImp();
    List<Admin> list = ad.findAllAdmins();
%>
<body>
<table border="1px" cellspacing="0" style="text-align: center;margin: 0 auto">
    <tr>
        <td>用户ID</td>
        <td>用户名</td>
        <td>密码</td>
    </tr>
    <%
        for (Admin a : list) {
    %>
    <tr>

        <td><%=a.getAdmin_id() %></td>
        <td><%=a.getAdmin_name() %></td>
        <td><%=a.getAdmin_password() %></td>
        <td><%=a.getAdmin_password() %></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
