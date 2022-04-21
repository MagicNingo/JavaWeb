<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%

	//得到从页面传过来的cookie
	Cookie[] arr = request.getCookies();
	List<String> list = new ArrayList();
	if (arr != null) {
		for (Cookie c : arr) {
			if (("goodsId-"+c.getValue()).equals(c.getName())) {
				list.add(c.getValue());
			}
		}
	}
	pageContext.setAttribute("list", list);
%>

<body>
	 <%--<h3> Cookie: <%=id %> </h3>--%>

	<table>

		<tr>
			<td>产品ID</td><td>操作</td>
		</tr>
		
		<c:forEach items="${list}" var="s">
			<tr>
				<td>${s}</td><td><a href="del.jsp?id=${s}">删除</a></td>
			</tr>
		</c:forEach>

	</table>

</body>


</html>





