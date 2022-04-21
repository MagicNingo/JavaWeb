<%@ page import="java.util.ArrayList" %>
<%@ page import="com.bus.entity.Admin" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2021/8/17
  Time: 11:19
  To change this template use MyFile | Settings | MyFile Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<%
    //字符数组
    String[] arr = {"jack","rose","mile","gigi"};
    pageContext.setAttribute("arr",arr);

    /*ArrayList<String> list = new ArrayList<>();
    list.add("A");
    list.add("B");
    list.add("C");
    list.add("D");
    pageContext.setAttribute("list", list);*/

    Admin admin = new Admin("jack","123456");
    pageContext.setAttribute("admin", admin);

    ArrayList<Admin> list = new ArrayList<>();
    list.add(new Admin("zhang","123456"));
    list.add(new Admin("wang","123456"));
    list.add(new Admin("li","123"));
    list.add(new Admin("ming","123345"));

    pageContext.setAttribute("list", list);

    HashMap<String, String> map = new HashMap<>();
    map.put("one", "1");
    map.put("two", "2");
    map.put("three", "3");
    pageContext.setAttribute("map", map);
%>
<body>
<h3>jstl测试</h3>

<h3>得到字符串</h3>
<p>${arr}</p>

<c:forEach items="${map}" var="m">
    <p>key:${m.key} =========> value:${m.value}</p>
</c:forEach>

<c:forEach items="${arr}" var="s" varStatus="vs">
    <%--<p>arr:----->${s}------->索引:${vs.index}---->计数:${vs.count}</p>--%>
    <c:if test="${s=='jack' || s=='rose'}">
        <p style="color: rebeccapurple">${s}</p>
    </c:if>
    <%-- 这里是没有else的，如果要实现else效果，写一个反向的条件即可   --%>
    <c:if test="${s!='jack' && s!='rose'}">
        <p style="color: #74d2d9">${s}</p>
    </c:if>
</c:forEach>

<c:forEach items="${arr}" var="s">
    <p>arr--------->${s}</p>
</c:forEach>
<hr>
<c:forEach items="${list}" var="s">
    <p>arr--------->${s}</p>
</c:forEach>
<%--
    这样写的含义是： 调用admin.getAdmin_name()
--%>
<p>${admin.admin_name}</p>
<p>${admin.admin_password}</p>
<%--直接调用对象的方法--%>
<p>${admin.getAdmin_password()}</p>
<p>${list.size()}</p>

</body>
</html>
