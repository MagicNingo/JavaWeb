<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%

    String id = request.getParameter("id");
    //数据存在cookie里面
    Cookie cookie = new Cookie("goodsId-"+id, id);
    //cookie默认关闭浏览器后会失效
    //设置cookie的存在时间
    cookie.setMaxAge(60*5);
    //cookie的路径问题：cookie的携带路径(生成路径为携带路径)
    //cookie.setPath("/");设成这样，访问让你和资源都会携带上cookie
    //把cookie发送回客户端
    response.addCookie(cookie);

%>

<body>
<a href="showCart.jsp">显示购物车</a>

</body>
</html>







