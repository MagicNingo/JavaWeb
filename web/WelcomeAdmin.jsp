<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2021/8/13
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>电子商务管理系统</title>
</head>
<style>
    h3 {
        text-align: center;
    }
</style>
<%
    String loginName = (String) session.getAttribute("loginName");

    String sid = session.getId();
    String cid = null;
    Cookie[] arr = request.getCookies();
    for (Cookie c : arr) {
        if ("JSESSIONID".equals(c.getName())) {
            cid = c.getValue();
        }
    }
%>
<body>
<h3>欢迎 <%=loginName%> 使用本系统！</h3>
<h3>SESSIONID :<%=sid%> </h3>
<h3>Cookie ID :<%=cid%> </h3>
<h3>当前在线人数：${onlineUser}</h3>
<h3>当前登录人数：${loginUser}</h3>
</body>
</html>
