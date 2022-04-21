<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.bus.entity.Product" %>
<%@ page import="com.bus.entity.Provider" %>
<%@ page import="com.bus.entity.Category" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2021/8/16
  Time: 8:11
  To change this template use MyFile | Settings | MyFile Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../LoginQuery.jsp" %>
<html>
<head>
    <title>电子商务管理系统</title>
    <script src="<%=request.getContextPath()%>/JavaScript/My97DatePicker/WdatePicker.js"></script>
</head>
<style>
    table {
        width: 70%;
        height: 90%;
        color: white;
        text-align: center;
        margin: 10px auto;
    }

    input {
        width: 100%;
        height: 100%;
    }

    select {
        width: 100%;
        height: 100%;
    }

    .ip {
        width: 20%;
        height: 80%;
    }

    #wh {
        width: 100%;
        height: 100%;
    }
</style>
<%--<%
    Product p = (Product) request.getAttribute("p");
    List<Provider> allProvider = (List<Provider>) request.getAttribute("allProvider");
    List<Category> allCategory = (List<Category>) request.getAttribute("allCategory");

%>--%>
<body>
<form id="addPForm" action="ProductServlet?op=doIncrease" method="post">
    <table border="1px" cellspacing="0">
        <tr>
            <td colspan="2"><h3>产品添加</h3></td>
        </tr>
        <tr>
            <td>产品名称</td>
            <td>
                <input type="text" name="product_name">
            </td>
        </tr>
        <tr>
            <td>产品进价</td>
            <td><input id="i_price" type="text" name="income_price" onblur="checkNumber()"></td>
        </tr>
        <tr>
            <td>供应商</td>
            <td>
                <select name="providerID">
                    <option value="0">请选择供应商</option>
                    <c:forEach items="${allProvider}" var="pro">
                        <option value="${pro.providerID}">${pro.provider_name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>产品数量</td>
            <td>
                <input id="tity" type="text" name="quantity" onblur="checkNumber()">
            </td>
        </tr>
        <tr>
            <td>产品售价</td>
            <td>
                <input id="s_price" type="text" name="sales_price" onblur="checkNumber()">
            </td>
        </tr>
        <tr>
            <td>产品目录</td>
            <td>
                <select name="categoryID">
                    <option value="0">请选择目录</option>
                    <c:forEach items="${allCategory}" var="c">
                        <option value="${c.categoryID}">${c.category_name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>进货时间</td>
            <td>
                <input id="wh" class="Wdate" type="text" name="income_time" onclick="WdatePicker()">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input class="ip" type="button" onclick="subForm()" value="确定添加">
                <input class="ip" type="reset" value="重置数据">
            </td>
        </tr>
    </table>
</form>
</body>
<script>
    function $(id) {
        return document.getElementsByName(id)[0];
    }

    function subForm() {
        if (isNull() & checkSelect()) {
            if (checkNumber($("income_price")) & checkInt($("quantity")) & checkNumber($("sales_price"))) {
                document.getElementById("addPForm").submit();
            } else {
                alert("产品进价,售价只能填写包含小数点后两位的数字且数量只能为整数！");
            }
        } else {
            alert("表中内容不能为空！");
        }
    }

    function isNull() {
        let isChange = false;
        let ele = document.getElementsByTagName('input');
        for (let i = 0; i < ele.length; i++) {
            let e = ele[i];
            if (e.type == 'text') {
                if (e.value != '') {
                    isChange = true;
                } else {
                    isChange = false;
                    break;
                }
            }
        }
        return isChange;
    }

    function checkNumber(obj) {
        let reg = /^[0-9]{1,6}[\.]?[0-9]{0,2}$/;
        let v = obj.value;
        let f = reg.test(v);
        return f;
    }

    function checkInt(obj) {
        let reg = /^[0-9]{0,10}$/;
        let v = obj.value;
        let f = reg.test(v);
        return f;
    }

    function checkSelect() {
        let f = false;
        let ele = document.getElementsByName('providerID')[0];
        let x = document.getElementsByName("categoryID")[0];
        let ops = ele.options;
        let op = x.options;
        let v = ops[ele.selectedIndex].value.trim();
        let value = op[x.selectedIndex].value.trim();
        if (v != 0 && value != 0) {
            f = true;
        }
        return f;
    }
</script>
</html>
