<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table width="60%" border="1px">
		<tr>
			<td>id</td><td>名称</td><td>操作</td>
		</tr>
		<tr>
			<td><input type="text" value="1" name="id" /></td><td>手机</td><td><a href="#" onclick="addToCart(1)">添加到购物车</a></td>
		</tr>
		<tr>
			<td><input type="text" value="2" name="id" /></td><td>电脑</td><td><a href="#" onclick="addToCart(2)">添加到购物车</a></td>
		</tr>
		<tr>
			<td><input type="text" value="3" name="id" /></td><td>键盘</td><td><a href="#" onclick="addToCart(3)">添加到购物车</a></td>
		</tr>
	
	</table>

<script type="text/javascript">
function addToCart(id){
	location.href="addToCart.jsp?id="+id;
}

</script>


</body>
</html>