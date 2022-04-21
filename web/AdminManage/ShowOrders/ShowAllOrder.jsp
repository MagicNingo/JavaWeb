<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<h3 style="text-align: center">订单显示</h3>
<div class="panel-body" style="padding-bottom:0px;">
    <div class="panel panel-default">
        <div class="panel-heading">查询条件</div>
        <div class="panel-body">
            <form id="formSearch" class="form-horizontal">
                <div class="form-group" style="margin-top:15px">
                    <label class="control-label col-sm-1" for="search_orderID"></label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="search_orderID" placeholder="请输入订单号">
                    </div>
                    <div class="col-sm-4" style="text-align:left;">
                        <button type="button" style="margin-left:10px"
                                class="btn btn-primary" onclick="searchOrder()">查询</button>
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

    function searchOrder() {
        $("#mytable").bootstrapTable('refresh' , {
            query : {
                offset: '0',
                limit: '5',
                'search_orderID' : $("#search_orderID").val()
            }
        })
    }

    function edit(id) {
        $.ajax({
            url: "OrderServlet",
            data: {
                "op": "doUpdateOrder",
                "orderID": id
            },
            type: "get",
            dataType: "json",
            success: function (jsonObj) {
                $("#productID").val(jsonObj.productID);
                $("#product_name").val(jsonObj.product_name);
                $("#income_price").val(jsonObj.income_price);
                $("#providerID").val(jsonObj.pro.providerID);

            }
        });
    }
    function doEdit() {
        $.ajax({
            url: "OrderServlet?op=doEdit",
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
            url: "OrderServlet",
            data: {
                "op": "doDeleteOrder",
                "orderID": id
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
                url: 'OrderServlet',         //请求后台的URL（*）
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
                    field: 'orderID',
                    title: '订单ID'
                }, {
                    field: 'order_date',
                    title: '订单日期'
                }, {
                    field: 'cus.customerID',
                    title: '客户ID'
                }, {
                    field: 'emp.empID',
                    title: '目录名称'
                }, {
                    field: 'orderID',
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
                'op' : 'doShowOrder',
                'search_orderID' : $("#search_orderID").val()
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
