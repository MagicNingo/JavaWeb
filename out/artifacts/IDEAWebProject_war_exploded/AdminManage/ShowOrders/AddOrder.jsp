<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单详情</title>
    <script src="../../JavaScript/jquery-1.12.4.js"></script>
</head>
<body>
    <table id="model" hidden>
        <tr>
            <td>
                <select name="productID" id="productID" onchange="">
                    <option value="0">选择产品</option>
                    <c:forEach items="${pList}" var="p">
                        <option value="${p.productID}">${p.product_name}</option>
                    </c:forEach>
                </select>
            </td>

            <td><input name="quantity" type="number"></td>

            <td>
                <select name="discount">
                    <option value="1">不打折</option>
                    <option value="0.9">九折</option>
                    <option value="0.8">八折</option>
                    <option value="0.7">七折</option>
                </select>
            </td>

            <td><a href="javascript:void(0)">删除</a></td>
        </tr>
    </table>

    <form id="myform">
        订单编号:<input type="text" id="orderID" name="orderID" readonly value="${orderID}">
        下单日期:<input class="Wdate" id="order_date" name="order_date" type="text" onclick="WdatePicker()">

        <select name="customerID" id="customerID">
            <option value="0">选择客户</option>
            <c:forEach items="${cusList}" var="cus">
                <option value="${cus.customerID}">${cus.customer_name}</option>
            </c:forEach>
        </select>

        <select name="empID" id="empID">
            <option value="0">选择员工</option>
            <c:forEach items="${empList}" var="emp">
                <option value="${emp.empID}">${emp.emp_name}</option>
            </c:forEach>
        </select>

        <table id="info" border="1px">
            <tr>
                <td>产品名称</td>
                <td>数量</td>
                <td>打折</td>
                <td>操作</td>
            </tr>

            <tr>
                <td colspan="6">
                    <a href="javascript:void(0)">增加数据</a>
                    <a href="javascript:void(0)">提交订单</a>
                </td>
            </tr>
        </table>
    </form>
</body>
<script>
    $(document).click(function (e){
        let value = e.target.innerHTML;
        if ("增加数据" == value) {
            $("#model tr").clone(true).insertBefore("tr:last");
        }
    })
</script>
</html>
