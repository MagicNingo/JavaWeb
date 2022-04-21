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

</style>
<body>
<form id="addCFrom" action="CategoryServlet?op=doIncrease" method="post">
    <table border="1px" cellspacing="0">
        <tr>
            <td colspan="3"><h3>目录添加</h3></td>
        </tr>
        <tr>
            <td>目录名</td>
            <td><input type="text" name="category_name" onblur="checkCateName(this)"></td>
            <td><span id="name_msg"></span></td>
        </tr>

        <tr>
            <td>目录描述</td>
            <td><input type="text" name="category_desc"></td>
            <td><span id=""></span></td>
        </tr>
        <tr>
            <td colspan="3">
                <input class="ip" type="button" onclick="subForm()" value="确定添加">
                <input class="ip" type="reset" value="重置数据">
            </td>
        </tr>
    </table>
</form>
</body>
<script>
    function subForm() {
        if (isNull()) {
            document.getElementById("addCFrom").submit();
        } else {
            alert("表中内容不能为空！")
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
    //ajax验证
    function checkName(obj) {
        let cateName = obj.value;
        xhttp.onreadystatechange = function () {
            if (this.readyState ==4 && this.status == 200) {
                if (this.responseText == "ok") {
                    $("#name_msg").html("<font color='green'>目录名可以使用！</font>");
                } else {
                    $("#name_msg").html("<font color='red'>目录名已存在！</font>");
                }
            }
        };
        xhttp.open("GET","./CategoryServlet?op=checkName&name="+cateName,true);
        xhttp.send();
    }
    //正则
    function checkCateName(obj) {
        const reg = /^[\u4e00-\u9fa5]{1,10}$/;
        let f = reg.test(obj.value);
        console.log(f);
        if (f) {
            checkName(obj);
        } else {
            $("#name_msg").html("<font color='red'>目录名应该是1-10位的中文！</font>");
        }
    }
</script>
</html>
