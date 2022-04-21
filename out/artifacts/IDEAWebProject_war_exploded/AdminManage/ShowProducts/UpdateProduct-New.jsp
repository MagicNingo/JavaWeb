<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script src="${pageContext.request.contextPath}/JavaScript/My97DatePicker/WdatePicker.js"></script>
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

    #wh {
        width: 100%;
        height: 100%;
    }
    span {
        font-size: 10px;
    }
</style>
<%--
<%
    Product p = (Product) request.getAttribute("p");
    List<Provider> allProvider = (List<Provider>) request.getAttribute("allProvider");
    List<Category> allCategory = (List<Category>) request.getAttribute("allCategory");

%>
--%>

<body>
<form id="updateForm" action="ProductServlet?op=doEdit" method="post">
    <table border="1px" cellspacing="0">
        <tr>
            <td colspan="2"><h3>产品修改</h3></td>
        </tr>
        <tr>
            <td>产品号</td>
            <td>
                <input type="text" name="productID" value="${p.productID}" readonly="readonly">
            </td>
            <td></td>
        </tr>
        <tr>
            <td>产品名称</td>
            <td>
                <input type="text" name="product_name" value="${p.product_name}">
            </td>
            <td><span id="p_name"></span></td>
        </tr>
        <tr>
            <td>产品进价</td>
            <td><input type="text" name="income_price" value="${p.income_price}" onblur="checkNumber(this)"></td>
            <td><span id="i_price"></span></td>
        </tr>
        <tr>
            <td>供应商</td>
            <td>
                <select name="providerID">
                    <option value="0">请选择供应商</option>
                    <c:forEach items="${allProvider}" var="pro">
                        <c:if test="${p.pro.providerID == pro.providerID}">
                            <option value="${pro.providerID}" selected>${pro.provider_name}</option>
                        </c:if>
                        <c:if test="${p.pro.providerID != pro.providerID}">
                            <option value="${pro.providerID}">${pro.provider_name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
            <td><span id="proID"></span></td>
        </tr>
        <tr>
            <td>产品数量</td>
            <td>
                <input type="text" name="quantity" value="${p.quantity}" onblur="checkInt(this)">
            </td>
            <td><span id="quan"></span></td>
        </tr>
        <tr>
            <td>产品售价</td>
            <td>
                <input type="text" name="sales_price" value="${p.sales_price}" onblur="checkNumber(this)">
            </td>
            <td><span id="s_price"></span></td>
        </tr>
        <tr>
            <td>产品目录</td>
            <td>
                <select name="categoryID">
                    <option value="0">请选择目录</option>
                    <c:forEach items="${allCategory}" var="c">
                        <c:if test="${p.cate.categoryID == c.categoryID}">
                            <option value="${c.categoryID}" selected>${c.category_name}</option>
                        </c:if>
                        <c:if test="${p.cate.categoryID != c.categoryID}">
                            <option value="${c.categoryID}">${c.category_name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
            <td><span id="cateID"></span></td>
        </tr>
        <tr>
            <td>进货时间</td>
            <td>
                <input id="wh" class="Wdate" type="text" name="income_time" value="${p.income_time}"
                       onclick="WdatePicker()">
            </td>
            <td></td>
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
    function subForm(){
        if (isChange() || isChange_select()){
            if (isNull()) {
                if (checkNumber($("income_price")) & checkInt($("quantity")) & checkNumber($("sales_price"))){
                    document.getElementById("updateForm").submit();
                }
            } else {
                alert("表中内容不能为空！");
            }
        }else {
            alert("尚未修改任何数据！");
        }
    }

    function isChange() {//判断form表单有没有变化,还可能有其他标签，需要的话再加，那个select必须设置一个默认选中的。
        let isChange = false;
        let ele = document.getElementsByTagName('input');
        for (let i = 0; i < ele.length; i++) {
            let e = ele[i];
            if (e.type == 'button'||e.type=='reset'||e.type=='submit') {
                continue;
            }else if(e.type == 'text') {
                if (e.value.trim() != e.defaultValue.trim()) {
                    isChange = true;
                    break;
                }
            }
        }
        return isChange;
    }
    function isChange_select() {
        let pro = document.getElementById("proID");
        let cate = document.getElementById("cateID");
        let f = false;
        let ele = document.getElementsByTagName('select');
            for (let i = 0; i < ele.length; i++) {
                let se = ele[i];
                let ops = se.options;
                let v = ops[se.selectedIndex].value.trim();
                for (let j = 0; j < ops.length; j++) {
                let e = ops[j];
                    if (e.defaultSelected && (e.value.trim()!= v)) {
                        if (v != 0){
                            f = true;
                            break;
                        } else {
                            pro.innerHTML = "<span>请选择供应商和目录！</span>";
                            cate.innerHTML = "<span>请选择供应商和目录！</span>";

                        }
                    }
                }
            }
        return f;
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
    function checkNumber(obj) {
        let reg = /^[0-9]{1,10}[\.]?[0-9]{0,2}$/;
        let v = obj.value;
        let f = reg.test(v);
        let i = document.getElementById("i_price");
        let s = document.getElementById("s_price");
        if (f) {
            return f;
        } else {
            i.innerHTML = "<span>产品进价只能填写包含<br>小数点后两位的数字!</span>";
            s.innerHTML = "<span>产品售价只能填写包含<br>小数点后两位的数字!</span>";
        }

        return f;
    }
    function checkInt(obj) {
        let reg = /^[0-9]{0,10}$/;
        let v = obj.value;
        let f = reg.test(v);
        let q = document.getElementById("quan");
        if (f) {
            return f;
        } else {
            q.innerHTML = "<span>产品数量只能填写0-10位的整数!</span>";

        }
        return f;
    }

</script>
</html>
