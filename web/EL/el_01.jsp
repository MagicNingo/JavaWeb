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
<%
        //四大作用域
    //pageContext描述的是当前页面的上下文
    pageContext.setAttribute("page_str", "hello01...");
    request.setAttribute("request_str", "hello02...");
    session.setAttribute("session_str", "hello03...");
    application.setAttribute("app_str", "hello04...");
    
    //pageContext可以把值设置到其他域中
    pageContext.setAttribute("other","测试01",PageContext.PAGE_SCOPE);
    pageContext.setAttribute("other","测试02",PageContext.REQUEST_SCOPE);
    pageContext.setAttribute("other","测试03",PageContext.SESSION_SCOPE);
    pageContext.setAttribute("other","测试04",PageContext.APPLICATION_SCOPE);

    String page_str = (String) pageContext.getAttribute("page_str");
    String request_str = (String) request.getAttribute("request_str");
    String session_str = (String) session.getAttribute("session_str");
    String app_str = (String) application.getAttribute("app_str");
%>
<body>
<h3>第一个页面</h3>
<a href="el_02.jsp">去第二个页面</a>
<a href="Test_jstl.jsp">去第jstl页面</a>

<hr/>
<p>当前页面：<%=page_str%></p>
<p>一次请求：<%=request_str%></p>
<p>一次会话：<%=session_str%></p>
<p>整个应用：<%=app_str%></p>

<%--<h3>EL表达式的自动取值的查找范围测试</h3>
<p>查找规则:根据作用域的范围，从小到大查找，如果找到了就不往后再找</p>
<p>pageContext:${pageScope.other}</p>
<p>pageContext:${requestScope.other}</p>
<p>pageContext:${sessionScope.other}</p>
<p>pageContext:${applicationScope.other}</p>--%>
</body>
</html>
