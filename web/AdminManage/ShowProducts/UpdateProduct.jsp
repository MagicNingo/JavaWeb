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
    <script src="<%=request.getContextPath()%>/JavaScript/My97DatePicker/WdatePicker.js"></script>
</head>
<style>
    table {
        width: 70%;
        height: 90%;
        color: white;
        text-align: center;
        margin: 50px 50px 10px 0;
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
<%
    Product p = (Product) request.getAttribute("p");
    List<Provider> allProvider = (List<Provider>) request.getAttribute("allProvider");
    List<Category> allCategory = (List<Category>) request.getAttribute("allCategory");

%>

<body>
<form action="ProductServlet?op=doEdit" method="post">
    <table border="1px" cellspacing="0">
        <tr>
            <td colspan="2"><h3>产品修改</h3></td>
        </tr>
        <tr>
            <td>产品号</td>
            <td>
                <input type="text" name="productID" value="<%=p.getProductID()%>" readonly="readonly">
            </td>
        </tr>
        <tr>
            <td>产品名称</td>
            <td>
                <input type="text" name="product_name" value="<%=p.getProduct_name()%>">
            </td>
        </tr>
        <tr>
            <td>产品进价</td>
            <td><input type="text" name="income_price" value="<%=p.getIncome_price()%>"></td>
        </tr>
        <tr>
            <td>供应商</td>
            <td>
                <select name="providerID">
                    <option value="0">请选择供应商</option>
                    <%
                        for (Provider pro : allProvider) {
                            if (p.getPro().getProviderID() == pro.getProviderID()) {
                    %>
                    <option value="<%=pro.getProviderID()%>" selected><%=pro.getProvider_name()%></option>
                    <%
                    } else {
                    %>
                    <option value="<%=pro.getProviderID()%>"><%=pro.getProvider_name()%></option>
                    <%
                            }
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr>
            <td>产品数量</td>
            <td>
                <input type="text" name="quantity" value="<%=p.getQuantity()%>">
            </td>
        </tr>
        <tr>
            <td>产品售价</td>
            <td>
                <input type="text" name="sales_price" value="<%=p.getSales_price()%>">
            </td>
        </tr>
        <tr>
            <td>产品目录</td>
            <td>
                <select name="categoryID">
                    <option value="0">请选择目录</option>
                    <%
                        for (Category c : allCategory) {
                            if (p.getCate().getCategoryID() == c.getCategoryID()) {
                    %>
                    <option value="<%=c.getCategoryID()%>" selected><%=c.getCategory_name()%></option>
                    <%
                    } else {
                    %>
                    <option value="<%=c.getCategoryID()%>"><%=c.getCategory_name()%></option>
                    <%
                            }
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr>
            <td>进货时间</td>
            <td>
                <input id="wh" class="Wdate" type="text" name="income_time" value="<%=p.getIncome_time()%>" onclick="WdatePicker()">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input class="ip" type="submit" value="确定修改">
                <input class="ip" type="reset" value="重置数据">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
