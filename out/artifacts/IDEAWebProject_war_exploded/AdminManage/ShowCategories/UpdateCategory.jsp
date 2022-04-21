<%@ page import="com.bus.service.ProductServiceImp" %>
<%@ page import="com.bus.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bus.entity.Provider" %>
<%@ page import="com.bus.entity.Category" %><%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2021/8/13
  Time: 11:10
  To change this template use MyFile | Settings | MyFile Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../LoginQuery.jsp" %>
<html>
<head>
    <title>电子商务管理系统</title>
    <link rel="stylesheet" href="../../CSS/page.css">
    <script src="../../JavaScript/jquery-1.12.4.js"></script>
</head>
<script>
    var xhttp;
    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        xhttp = new ActiveXObject("Microsoft.XMLHttp");
    }
</script>
<style>
    table {
        width: 70%;
        height: 90%;
        color: white;
        text-align: center;
        margin: 20px 0 0 70px;
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
</style>
<%--
<%
    Category c = (Category) request.getAttribute("cate");
%>
--%>

<body>
<form id="cateForm" action="CategoryServlet?op=doEdit" method="post">
    <table border="1px" cellspacing="0">
        <tr>
            <td colspan="2"><h3>目录修改</h3></td>
        </tr>
        <tr>
            <td>目录号</td>
            <td>
                <input type="text" name="categoryID" value="${cate.categoryID}" readonly="readonly">
            </td>
        </tr>
        <tr>
            <td>目录名</td>
            <td>
                <input type="text" name="category_name" value="${cate.category_name}" onblur="checkCateName(this)">
            </td>
        </tr>

        <tr>
            <td>备注</td>
            <td><input type="text" name="category_desc" value="${cate.category_desc}"></td>
        </tr>


        <tr>
            <td colspan="2">
                <input class="ip" type="button" onclick="subForm()" value="确定修改">
                <input class="ip" type="reset" value="重置数据">
            </td>
        </tr>
    </table>
</form>
</body>
<script>
    function subForm() {
        if (isChange()) {
            if (isNull()) {
                document.getElementById("cateForm").submit();
            } else {
                alert("表中内容不能为空！")
            }
        } else {
            alert("尚未修改任何数据！");
        }
    }

    function isChange() {
        let isChange = false;
        let ele = document.getElementsByTagName('input');
        for (let i = 0; i < ele.length; i++) {
            let e = ele[i];
            if (e.type == 'button' || e.type == 'reset' || e.type == 'submit') {
                continue;
            } else if (e.type == 'text') {
                if (e.value.trim() != e.defaultValue.trim()) {
                    isChange = true;
                    break;
                }
            }
        }
        return isChange;
    }

    function isNull() {
        let isChange = false;
        let ele = document.getElementsByTagName('input');
        for (let i = 0; i < ele.length; i++) {
            let e = ele[i];
            if (e.type == 'text') {
                if (e.value.trim() != '') {
                    isChange = true;
                } else {
                    isChange = false;
                    break;
                }
            }
        }
        return isChange;
    }
    function checkCateName(obj) {
        let n = obj.value;
        xhttp.onreadystatechange = function () {
            if (this.readyState ==4 && this.status == 200) {
                alert(this.responseText);
            }
        };
        xhttp.open("GET","./CategoryServlet?op=checkNameByUpdate&name="+n+"&id="+$("input[name = 'categoryID']").val(),true);
        xhttp.send();
    }

</script>
</html>
