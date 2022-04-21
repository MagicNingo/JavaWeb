<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
    //1.删除cookie的规则：新建一个同名的cookie.
    //2.并设置过期时间为0
    //3.要写回到客户端，把原来的cookie覆盖
    String id = request.getParameter("id");
    Cookie cookie =  new Cookie("goodsId-"+id, null);
    cookie.setMaxAge(0);
    response.addCookie(cookie);
%>
<body>
<h3>从购物车删除</h3>

</body>
</html>