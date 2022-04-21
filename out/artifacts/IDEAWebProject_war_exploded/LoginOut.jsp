
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    // 退出登录的本质:就是把存在session里的用户的登录移除即可
    session.removeAttribute("loginName");
    out.write("<script>location.href = 'AdminLogin-New.jsp'</script>");
%>
<body>

</body>
</html>
