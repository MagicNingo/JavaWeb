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
</head>
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
    span {
        font-size: 10px;
    }
</style>
<%--
<%
    Provider pro = (Provider) request.getAttribute("pro");
%>
--%>

<body>
<form id="proFrom" action="ProviderServlet?op=doEdit" method="post">
    <table border="1px" cellspacing="0">
        <tr>
            <td colspan="3"><h3>供应商修改</h3></td>
        </tr>
        <tr>
            <td>供应商号</td>
            <td>
                <input type="text" name="providerID" value="${pro.providerID}" readonly="readonly">
            </td>
            <td></td>
        </tr>
        <tr>
            <td>供应商名</td>
            <td>
                <input type="text" name="provider_name" value="${pro.provider_name}">
            </td>
            <td><span id="pro_name"></span></td>
        </tr>
        <tr>
            <td>供应商地址</td>
            <td><input type="text" name="provider_add" value="${pro.provider_add}"></td>
            <td><span id="add"></span></td>
        </tr>

        <tr>
            <td>供应商联系电话</td>
            <td>
                <input type="text" name="provider_tel" value="${pro.provider_tel}">
            </td>
            <td><span id="tel"></span></td>
        </tr>
        <tr>
            <td>供应商账户</td>
            <td>
                <input type="text" name="account" value="${pro.account}">
            </td>
            <td><span id="count"></span></td>
        </tr>

        <tr>
            <td>供应商邮件</td>
            <td>
                <input type="text" name="email" value="${pro.email}" onblur="checkEmail(this)">
            </td>
            <td><span id="mail" ></span></td>
        </tr>
        <tr>
            <td colspan="3">
                <input class="ip" type="button" onclick="subForm()" value="确定修改">
                <input class="ip" type="reset" value="重置数据">
            </td>
        </tr>
    </table>
</form>
</body>
<script>
    function $(id){
        return document.getElementsByName(id)[0];
    }
    function subForm() {
        if (isChange()) {
            if (isNull()) {
                if (checkEmail($("email"))){
                    document.getElementById("proFrom").submit();
                }
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
    function checkEmail(obj) {
        let reg = /^\w{6,18}@[A-Z|a-z|1-9]{2,10}\.[A-Z|a-z]{2,3}[\.]?[A-Z|a-z]{0,3}$/;
        let v = obj.value;
        let f = reg.test(v);
        let span = document.getElementById("mail");
        if (f) {
            span.innerHTML = "<span>邮箱符合规则！</span>";
        } else {
            span.innerHTML = "<span>邮箱由6-18位字母,数字和下划线组成<br>且必须包含@和后缀名!</span>";
        }
        return f;
    }
</script>
</html>
