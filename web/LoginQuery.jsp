<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2021/8/13
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getContextPath();
%>
<%
    String name = (String) session.getAttribute("loginName");
    if (name == null) {
        out.print("<script>alert('请您先进行登录！'); location.href = '"+basePath+"/AdminLogin-New.jsp'</script>");
    }
%>
