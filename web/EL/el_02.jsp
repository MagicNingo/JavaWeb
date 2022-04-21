<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2021/8/17
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%--<%
   //原生的java方式取值
    String page_str = (String) pageContext.getAttribute("page_str");
    String request_str = (String) request.getAttribute("request_str");
    String session_str = (String) session.getAttribute("session_str");
    String app_str = (String) application.getAttribute("app_str");

    //取pageContext设下的其它域的值
    String other03 = (String) session.getAttribute("other");
    String other04 = (String) application.getAttribute("other");
%>--%>
<body>
<h3>第二个页面</h3>
//EL表达式的应用
<p>当前页面：${page_str}</p>
<p>一次请求：${request_str}</p>
<p>一次会话：${session_str}</p>
<p>整个应用：${app_str}</p>
<hr/>

<h3>EL表达式的自动取值的查找范围测试</h3>
<p>查找规则:根据作用域的范围，从小到大查找，如果找到了就不往后再找</p>
<p>pageContext:${other}</p>
</body>
</html>
