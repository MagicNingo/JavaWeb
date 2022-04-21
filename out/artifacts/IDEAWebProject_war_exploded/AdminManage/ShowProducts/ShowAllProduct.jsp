<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../LoginQuery.jsp" %>
<html>
<head>
    <title>电子商务管理系统</title>
    <link rel="stylesheet" href="../../JavaScript/bootstrap-3.4.1/dist/css/bootstrap.css">
    <script src="../../JavaScript/jquery-1.12.4.js"></script>
    <script src="../../JavaScript/bootstrap-3.4.1/dist/js/bootstrap.js"></script>
    <link href="../../JavaScript/dist/bootstrap-table.css" rel="stylesheet"/>
    <script src="../../JavaScript/dist/bootstrap-table.js"></script>
    <script src="../../JavaScript/dist/bootstrap-table-zh-CN.js"></script>
    <script src="../../JavaScript/My97DatePicker/WdatePicker.js"></script>
</head>
<style>
 .Wdate {
     display: block;
     width: 100%;
     height: 34px;
     padding: 6px 12px;
     font-size: 14px;
     line-height: 1.42857143;
     color: #555555;
     background-color: #fff;
     border: 1px solid #ccc;
     border-radius: 4px;
 }
</style>

<body>
<h3 style="text-align: center">商品显示</h3>
<div class="panel-body" style="padding-bottom:0px;">
    <div class="panel panel-default">
        <div class="panel-heading">查询条件</div>
        <div class="panel-body">
            <form id="formSearch" class="form-horizontal">
                <div class="form-group" style="margin-top:15px">
                    <label class="control-label col-sm-1" for="search_productName"></label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="search_productName" placeholder="请输入产品名">
                    </div>
                    <div class="col-sm-4" style="text-align:left;">
                        <button type="button" style="margin-left:10px"
                                class="btn btn-primary" onclick="searchProduct()">查询</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<table id="mytable"></table>

<!-- Modal(修改的模态框) -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">修改界面</h4>
            </div>
            <div class="modal-body">
                <form class="form form-horizontal">
                    <div class="form-group">
                        <label for="productID" class="col-sm-3 control-label">商品ID</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="productID" name="productID">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="product_name" class="col-sm-3 control-label">商品名称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="product_name" name="product_name"
                                   placeholder="请输入商品名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="income_price" class="col-sm-3 control-label">商品进价</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="income_price" name="income_price"
                                   placeholder="请输入商品进价">
                        </div>
                    </div>
                    <div class="form-group" style="display: none">
                        <label for="providerID" class="col-sm-3 control-label">供应商ID</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="providerID" name="providerID"
                                   placeholder="请输入供应商名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="provider_name" class="col-sm-3 control-label">供应商名称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="provider_name" name="provider_name"
                                   placeholder="请输入供应商名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="quantity" class="col-sm-3 control-label">商品数量</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="quantity" name="quantity" placeholder="请输入商品数量">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sales_price" class="col-sm-3 control-label">商品售价</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="sales_price" name="sales_price" placeholder="请输入商品售价">
                        </div>
                    </div>
                    <div class="form-group" style="display: none">
                        <label for="categoryID" class="col-sm-3 control-label">目录ID</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="categoryID" name="categoryID" placeholder="请输入目录ID">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="category_name" class="col-sm-3 control-label">目录名</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="category_name" name="category_name" placeholder="请输入目录名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="income_time" class="col-sm-3 control-label">进货时间</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control Wdate" onclick="WdatePicker()" id="income_time" name="income_time" placeholder="请输入进货时间">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="doEdit()">Save changes</button>
            </div>
        </div>
    </div>
</div>
</body>
<script>

    function searchProduct() {
        $("#mytable").bootstrapTable('refresh' , {
            query : {
                offset: '0',
                limit: '5',
                'search_name' : $("#search_productName").val()
            }
        })
    }

    function edit(id) {
        $.ajax({
            url: "ProductServlet",
            data: {
                "op": "doUpdateProduct",
                "productID": id
            },
            type: "get",
            dataType: "json",
            success: function (jsonObj) {
                $("#productID").val(jsonObj.productID);
                $("#product_name").val(jsonObj.product_name);
                $("#income_price").val(jsonObj.income_price);
                $("#providerID").val(jsonObj.pro.providerID);
                $("#provider_name").val(jsonObj.pro.provider_name);
                $("#quantity").val(jsonObj.quantity);
                $("#sales_price").val(jsonObj.sales_price);
                $("#categoryID").val(jsonObj.cate.categoryID);
                $("#category_name").val(jsonObj.cate.category_name);
                $("#income_time").val(jsonObj.income_time);
            }
        });
    }
    function doEdit() {
        $.ajax({
            url: "ProductServlet?op=doEdit",
            data: $("form").serialize(),
            type: "get",
            success: function (msg) {
                if (msg == "ok") {
                    $("#myModal").modal('hide');//修改成功后关闭模态框
                    $("#mytable").bootstrapTable("refresh");//刷新表格
                } else {
                    alert("修改失败！");
                }
            }
        });
    }

    function del(id) {
        $.ajax({
            url: "ProductServlet",
            data: {
                "op": "doDeleteProduct",
                "productID": id
            },
            type: "get",
            success: function (msg) {
                if (msg == "ok") {
                    $("#mytable").bootstrapTable("refresh");//刷新表格
                    alert("删除成功！");
                } else {
                    alert("删除失败！");
                }
            }
        });
    }

    $(function () {
        let t = new Mytable();
        t.init();
    });
    var Mytable = function () {
        let obj = new Object();
        obj.init = function () {
            //加载表格的代码
            $('#mytable').bootstrapTable({
                url: 'ProductServlet',         //请求后台的URL（*）
                method: 'get',                      //请求方式（*）
                toolbar: '#toolbar',                //工具按钮用哪个容器
                //striped: true,                      //是否显示行间隔色
                //cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                //sortable: false,                     //是否启用排序
                //sortOrder: "asc",                   //排序方式
                queryParams: obj.queryParams,//传递参数（*）
                sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                       //初始化加载第一页，默认第一页
                pageSize: 5,                       //每页的记录行数（*）
                pageList: [5, 10, 25, 50, 100],        //可供选择的每页的行数（*）
                search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
                //strictSearch: true,
                showColumns: true,                  //是否显示所有的列
                showRefresh: true,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
                //height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
                //showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
                //cardView: false,                    //是否显示详细视图
                //detailView: false,                   //是否显示父子表
                columns: [{
                    checkbox: true
                }, {
                    field: 'productID',
                    title: '商品ID'
                }, {
                    field: 'product_name',
                    title: '商品名称'
                }, {
                    field: 'income_price',
                    title: '商品进价'
                },{
                    field: 'pro.provider_name',
                    title: '供应商名称'
                }, {
                    field: 'quantity',
                    title: '商品数量'
                }, {
                    field: 'sales_price',
                    title: '商品售价'
                },{
                    field: 'cate.category_name',
                    title: '目录名称'
                },{
                    field: 'income_time',
                    title: '进货时间'
                }, {
                    field: 'productID',
                    title: '操作',
                    formatter: editAndDel
                }]
            });
        }

        //加载表格的参数
        obj.queryParams = function (params) {
            var temp = {
                limit : params.limit,
                offset : params.offset,
                'op' : 'doShowProduct',
                'search_name' : $("#search_productName").val()
            }
            return temp;
        }

        return obj;
    }
    function editAndDel(id, row, index) {
        return `
            <a href="#" class="glyphicon glyphicon-pencil" data-toggle="modal" data-target="#myModal" onclick="edit(` + id + `)"></a>
            &nbsp;&nbsp;
            <a href="#" class="glyphicon glyphicon-trash" onclick="del(` + id + `)"></a>
       `;
    }
</script>
</html>
<!--
<script>

function displayAllProduct(currentPage) {
if (!currentPage) {
currentPage = 1;
}
$.ajax({
url : "ProductServlet",
data : {
"op" : "doShowProduct",
"currentPage" : currentPage,
"pageSize" : "6"
},
type : "get",
success : function (msg) {

let jsonArray =JSON.parse(msg);
let arr = jsonArray.list;

//把数据写到表里的时候，应该先清除原来的数据
$("tr:not(:first)").remove();
//把数据加载到表格里
let t = $("#mytable")[0];
let row;
for (let p of arr) {
row = t.insertRow();
row.insertCell().innerHTML = p.productID;
row.insertCell().innerHTML = p.product_name;
row.insertCell().innerHTML = p.income_price;
row.insertCell().innerHTML = p.pro.provider_name;
row.insertCell().innerHTML = p.quantity;
row.insertCell().innerHTML = p.cate.category_name;
row.insertCell().innerHTML = p.sales_price;
row.insertCell().innerHTML = p.income_time;
row.insertCell().innerHTML = `
<a href="ProductServlet?op=doDeleteProduct&currentPage=`+jsonArray.currentPage+`&ProductID=`+p.productID+`">删除</a>
&nbsp;&nbsp;
<a href="ProductServlet?op=doUpdateProduct&currentPage=1&ProductID=`+p.productID+`">修改</a>
&nbsp;&nbsp;
<a href="ProductServlet?op=doAddProduct&currentPage=1&ProductID=`+p.productID+`">添加</a>
`;
}
t.insertRow().innerHTML = `
<td colspan="9">
<a href="javascript:void(0)" onclick="displayAllProduct(`+jsonArray.upPage+`)">上一页</a>
&nbsp;&nbsp;
第`+jsonArray.currentPage+`页
&nbsp;/&nbsp;
共`+jsonArray.totalPage+`页
&nbsp;&nbsp;
<a href="javascript:void(0)" onclick="displayAllProduct(`+jsonArray.nextPage+`)">下一页</a>
</td>
`;
}
});

}

</script>
-->
<!--
<tr>
    <td>产品号</td>
    <td>产品名</td>
    <td>产品进价</td>
    <td>供应商</td>
    <td>产品数量</td>
    <td>所属目录</td>
    <td>产品售价</td>
    <td>进货时间</td>
    <td>操作选项</td>
</tr>
-->
<%--<c:forEach items="${list.list}" var="p">
    <tr>
        <td>${p.productID}
        </td>
        <td>${p.product_name}
        </td>
        <td>${p.income_price}
        </td>
        <td>${p.pro.provider_name}
        </td>
        <td>${p.quantity}
        </td>
        <td>${p.cate.category_name}
        </td>
        <td>${p.sales_price}
        </td>
        <td>${p.income_time}
        </td>
        <td>
            <a href="ProductServlet?op=doDeleteProduct&currentPage=${list.currentPage}&ProductID=${p.productID}">删除</a>
            &nbsp;&nbsp;
            <a href="ProductServlet?op=doUpdateProduct&currentPage=1&ProductID=${p.productID}">修改</a>
            &nbsp;&nbsp;
            <a href="ProductServlet?op=doAddProduct&currentPage=1&ProductID=${p.productID}">添加</a>
        </td>
    </tr>
    </c:forEach>
    <tr>
        <td colspan="9">
            <c:if test="${list.currentPage > 1}">
                <a href="ProductServlet?op=doShowProduct&currentPage=${list.upPage}&pageSize=6">上一页</a>
            </c:if>
            <c:if test="${list.currentPage <= 1}">
                <a  onclick="upClick()">上一页</a>
            </c:if>
            &nbsp;&nbsp;
            第${list.currentPage}页
            &nbsp;/&nbsp;
            共${list.totalPage}页
            &nbsp;&nbsp;
            <c:if test="${list.currentPage != list.totalPage}">
                <a href="ProductServlet?op=doShowProduct&currentPage=${list.nextPage}&pageSize=6">下一页</a>
            </c:if>

            <c:if test="${list.currentPage == list.totalPage}">
                <a onclick="nextClick()">下一页</a>
            </c:if>
        </td>
    </tr>--%>
<!--
ProductServlet?op=doShowProduct&currentPage=`+jsonArray.nextPage+`&pageSize=6
xhttp.onreadystatechange = function () {
if (this.readyState == 4 && this.status == 200) {}
}
xhttp.open("GET", "./ProductServlet?op=doShowProduct&currentPage=1&pageSize=6", true);
xhttp.send();-->