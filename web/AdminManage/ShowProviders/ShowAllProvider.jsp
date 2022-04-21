<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<h3 style="text-align: center">供应商显示</h3>
<div class="panel-body" style="padding-bottom:0px;">
    <div class="panel panel-default">
        <div class="panel-heading">查询条件</div>
        <div class="panel-body">
            <form id="formSearch" class="form-horizontal">
                <div class="form-group" style="margin-top:15px">
                    <label class="control-label col-sm-1" for="search_providerName"></label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="search_providerName" placeholder="请输入供应商名">
                    </div>
                    <div class="col-sm-4" style="text-align:left;">
                        <button type="button" style="margin-left:10px"
                                class="btn btn-primary" onclick="searchProvider()">查询</button>
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
                        <label for="providerID" class="col-sm-3 control-label">供应商ID</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="providerID" name="providerID">
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
                        <label for="provider_add" class="col-sm-3 control-label">供应商地址</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="provider_add" name="provider_add"
                                   placeholder="请输入供应商地址">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="provider_tel" class="col-sm-3 control-label">供应商联系方式</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="provider_tel" name="provider_tel"
                                   placeholder="请输入供应商联系方式">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="account" class="col-sm-3 control-label">供应商账户</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="account" name="account" placeholder="请输入供应商账户">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-3 control-label">供应商邮箱</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="email" name="email" placeholder="请输入供应商邮箱">
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

    function searchProvider() {
        $("#mytable").bootstrapTable('refresh' , {
            query : {
                offset: '0',
                limit: '5',
                'search_name' : $("#search_providerName").val()
            }
        })
    }

    function edit(id) {
        $.ajax({
            url: "ProviderServlet",
            data: {
                "op": "doUpdateProvider",
                "providerID": id
            },
            type: "get",
            dataType: "json",
            success: function (jsonObj) {
                $("#providerID").val(jsonObj.providerID);
                $("#provider_name").val(jsonObj.provider_name);
                $("#provider_add").val(jsonObj.provider_add);
                $("#provider_tel").val(jsonObj.provider_tel);
                $("#account").val(jsonObj.account);
                $("#email").val(jsonObj.email);
            }
        });
    }

    function doEdit() {
        $.ajax({
            url: "ProviderServlet?op=doEdit",
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
            url: "ProviderServlet",
            data: {
                "op": "doDeleteProvider",
                "providerID": id
            },
            type: "get",
            success: function (msg) {
                if (msg == "1") {
                    $("#mytable").bootstrapTable("refresh");//刷新表格
                    alert("删除成功！");
                } else if (msg == "0") {
                    alert("删除失败！");
                } else {
                    alert("该供应商不可删除！");
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
                url: 'ProviderServlet',         //请求后台的URL（*）
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
                    field: 'providerID',
                    title: '供应商ID'
                }, {
                    field: 'provider_name',
                    title: '供应商名称'
                }, {
                    field: 'provider_add',
                    title: '供应商地址'
                }, {
                    field: 'provider_tel',
                    title: '供应商电话'
                }, {
                    field: 'account',
                    title: '供应商账户'
                }, {
                    field: 'email',
                    title: '供应商邮箱'
                }, {
                    field: 'providerID',
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
                'op' : 'doShowProvider',
                'search_name' : $("#search_providerName").val()
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
<%--<table border="1px" cellspacing="0">
    <tr>
        <td>供应商号</td>
        <td>供应商名</td>
        <td>供应商地址</td>
        <td>供应商联系电话</td>
        <td>供应商账户</td>
        <td>供应商邮件</td>
        <td>操作选项</td>
    </tr>
<c:forEach items="${list.list}" var="p">
        <tr>
            <td>${p.providerID}
            </td>
            <td>${p.provider_name}
            </td>
            <td>${p.provider_add}
            </td>
            <td>${p.provider_tel}
            </td>
            <td>${p.account}
            </td>
            <td>${p.email}
            </td>
            <td>
                <a href="ProviderServlet?op=doDeleteProvider&currentPage=${list.currentPage}&ProviderID=${p.providerID}">删除</a>
                &nbsp;&nbsp;
                <a href="ProviderServlet?op=doUpdateProvider&currentPage=1&ProviderID=${p.providerID}">修改</a>
                &nbsp;&nbsp;
                <a href="ProviderServlet?op=doAddProvider&currentPage=1&ProviderID=${p.providerID}">添加</a>
            </td>
        </tr>
</c:forEach>
    <tr>
        <td colspan="9">
            <c:if test="${list.currentPage > 1}">
                <a href="ProviderServlet?op=doShowProvider&currentPage=${list.upPage}&pageSize=6">上一页</a>
            </c:if>

            <c:if test="${list.currentPage <= 1}">
                <a onclick="upClick()">上一页</a>
            </c:if>
            &nbsp;&nbsp;
            第${list.currentPage}页
            &nbsp;/&nbsp;
            共${list.totalPage}页
            &nbsp;&nbsp;
            <c:if test="${list.currentPage != list.totalPage}">
                <a href="ProviderServlet?op=doShowProvider&currentPage=${list.nextPage}&pageSize=6">下一页</a>
            </c:if>

            <c:if test="${list.currentPage == list.totalPage}">
                <a onclick="nextClick()">下一页</a>
            </c:if>
        </td>
    </tr>
</table>--%>
<%--<script>
    function upClick() {
        alert("当前已经是第一页！");
    }

    function nextClick() {
        alert("当前已经是最后一页！");
    }
</script>--%>
