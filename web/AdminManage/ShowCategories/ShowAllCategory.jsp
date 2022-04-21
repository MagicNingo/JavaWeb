<%@ page import="com.bus.service.CategoryServiceImp" %>
<%@ page import="com.bus.entity.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bus.util.Page" %><%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2021/8/13
  Time: 19:02
  To change this template use MyFile | Settings | MyFile Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../LoginQuery.jsp"%>
<html>
<head>
    <title>电子商务管理系统</title>
    <link rel="stylesheet" href="../../JavaScript/bootstrap-3.4.1/dist/css/bootstrap.css">
    <script src="../../JavaScript/jquery-1.12.4.js"></script>
    <script src="../../JavaScript/bootstrap-3.4.1/dist/js/bootstrap.js"></script>

    <link href="../../JavaScript/dist/bootstrap-table.css" rel="stylesheet"/>
    <script src="../../JavaScript/dist/bootstrap-table.js"></script>
    <script src="../../JavaScript/dist/bootstrap-table-zh-CN.js"></script>
</head>
<style>
    .fixed-table-container table {
        width: 100%;
    }

    body {
        width: 100%;
    }
</style>


<body>
<h3 style="text-align: center">目录显示</h3>
<div class="panel-body" style="padding-bottom:0px;">
    <div class="panel panel-default">
        <div class="panel-heading">查询条件</div>
        <div class="panel-body">
            <form id="formSearch" class="form-horizontal">
                <div class="form-group" style="margin-top:15px">
                    <label class="control-label col-sm-1" for="search_categoryName"></label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="search_categoryName" placeholder="请输入目录名">
                    </div>
                    <div class="col-sm-4" style="text-align:left;">
                        <button type="button" style="margin-left:10px"
                                class="btn btn-primary" onclick="searchCategory()">查询</button>
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
                        <label for="categoryID" class="col-sm-3 control-label">目录ID</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="categoryID" name="categoryID">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="category_name" class="col-sm-3 control-label">目录名称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="category_name" name="category_name"
                                   placeholder="请输入目录名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="category_desc" class="col-sm-3 control-label">目录备注</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="category_desc" name="category_desc"
                                   placeholder="请输入目录备注">
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

    function searchCategory() {
        $("#mytable").bootstrapTable('refresh' , {
            query : {
                offset: '0',
                limit: '5',
                'search_name' : $("#search_categoryName").val()
            }
        })
    }

    function edit(id) {
        $.ajax({
            url: "CategoryServlet",
            data: {
                "op": "doUpdateCategory",
                "categoryID": id
            },
            type: "get",
            dataType: "json",
            success: function (jsonObj) {
                $("#categoryID").val(jsonObj.categoryID);
                $("#category_name").val(jsonObj.category_name);
                $("#category_desc").val(jsonObj.category_desc);
            }
        });
    }

    function doEdit() {
        $.ajax({
            url: "CategoryServlet?op=doEdit",
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
            url: "CategoryServlet",
            data: {
                "op": "doDeleteCategory",
                "categoryID": id
            },
            type: "get",
            success: function (msg) {
                if (msg == "1") {
                    $("#mytable").bootstrapTable("refresh");//刷新表格
                    alert("删除成功！");
                } else if (msg == "0") {
                    alert("删除失败！");
                } else {
                    alert("该目录不可删除！");
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
                url: 'CategoryServlet',         //请求后台的URL（*）
                method: 'get',                      //请求方式（*）
                toolbar: '#toolbar',                //工具按钮用哪个容器
                pagination: true,                   //是否显示分页（*）
                queryParams: obj.queryParams,      //传递参数（*）
                sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                       //初始化加载第一页，默认第一页
                pageSize: 5,                       //每页的记录行数（*）
                pageList: [5, 10, 25, 50, 100],        //可供选择的每页的行数（*）
                //search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
                showColumns: true,                  //是否显示所有的列
                showRefresh: true,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
                uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
                columns: [{
                    checkbox: true
                }, {
                    field: 'categoryID',
                    title: '供应商ID'
                }, {
                    field: 'category_name',
                    title: '供应商名称'
                }, {
                    field: 'category_desc',
                    title: '供应商地址'
                }, {
                    field: 'categoryID',
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
                'op' : 'doShowCategory',
                'search_name' : $("#search_categoryName").val()
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
