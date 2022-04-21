<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2021/8/9
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>电子商务管理系统</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">
    <link rel="stylesheet" href="JavaScript/bootstrap-3.4.1/dist/css/bootstrap.css">
</head>
<style>
    body{
        background: url("Image/QYQX.jpg") no-repeat;
        background-size:100% auto;
    }
    #login-box{
        width:40%;
        height:auto;
        margin: 13% auto 0;
        text-align:center;
        background:#00000060;
        padding:20px 50px;
    }
    #login-box h1{
        color:#fff;
    }
    #login-box span {
        font-size: 10px;
    }
    #login-box .form .item{
        margin-top:15px;
    }
    #login-box .form .item i{
        font-size:18px;
        color:#fff;
    }
    #login-box .form .item input{
        width:180px;
        font-size:18px;
        border:0;
        border-bottom:2px solid #fff;
        padding:5px 10px;
        background:#ffffff00;
        color:#fff;
    }
    #login-box 	button{
        margin-top:20px;
        width:190px;
        height:30px;
        font-size:20px;
        font-weight:700;
        color:#fff;
        background-image: linear-gradient(to right, #74ebd5 0%, #9face6 100%);
        border:0;
        border-radius:15px;
    }
    .form-control {
        width: 200px;
        margin: 20px auto 0;
    }
</style>

<body>
<script>
    if (top.location != self.location) {
        top.location = self.location;
    }
</script>
<form id="AdminForm" action="AdminLoginServlet" method="post">
    <div id="login-box">
        <h1>Login</h1>
        <div class="form">
            <div class="item">
                <i class="fa fa-github-alt" style="font-size:24px"></i>
                <input id="adminName" type="text" name="username" placeholder="UserName" onblur="checkAdminName(this)"/>
            </div>
            <span id="name"></span>
            <div class="item">
                <i class="fa fa-search" style="font-size:24px"></i>
                <input id="adminPassword" type="password" name="password" placeholder="Password" onblur="checkAdminPassword(this)"/>
            </div>
            <span id="pass"></span>
        </div>
        <select name="time" class="form-control">
            <option value="1">不保持登录</option>
            <option value="2">一小时之内免登录</option>
            <option value="3">一天之内免登录</option>
            <option value="4">十天之内免登录</option>
        </select>
        <button type="button" onclick="subForm()">Login</button>

    </div>
</form>
</body>
<script>
    function $(id) {
        return document.getElementById(id);
    }
    function subForm() {
        //方式一:
        /* if (checkName($("user")) & checkPassword($("pass"))) {
            return true;
        }
        return false; */
        //方式二:
        if (checkAdminName($("adminName")) & checkAdminPassword($("adminPassword"))) {
            document.getElementById("AdminForm").submit();
        }

    }
    function checkAdminName(obj){
        const reg = /^[\u4e00-\u9fa5]{2,8}$/;
        const v = obj.value;
        const f = reg.test(v);
        const span = document.getElementById("name");
        if (f) {
            span.innerHTML = "<span style='color: greenyellow;'>用户名符合规则</span>";
        } else {
            span.innerHTML = "<span style='color: red;'>用户名必须是2-8位的中文</span>";
        }
        return f;
    }
    function checkAdminPassword(obj) {
        const reg = /^[A-Z|a-z|0-9]{6,17}$/;
        const v = obj.value;
        const f = reg.test(v);
        const span = document.getElementById("pass");
        if (f) {
            span.innerHTML = "<span style='color: greenyellow;'>密码符合规则</>";
        } else {
            span.innerHTML = "<span style='color: red;'>密码必须是6-18位的数字或字母</span>";
        }
        return f;
    }

</script>
</html>
