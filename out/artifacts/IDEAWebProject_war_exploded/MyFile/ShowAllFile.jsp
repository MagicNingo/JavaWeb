<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2021/10/7
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传与下载</title>
    <link rel="stylesheet" href="../JavaScript/bootstrap-3.4.1/dist/css/bootstrap.css">
    <script src="../JavaScript/jquery-1.12.4.js"></script>
    <script src="../JavaScript/bootstrap-3.4.1/dist/js/bootstrap.js"></script>

    <link href="../JavaScript/dist/bootstrap-table.css" rel="stylesheet"/>
    <script src="../JavaScript/dist/bootstrap-table.js"></script>
    <script src="../JavaScript/dist/bootstrap-table-zh-CN.js"></script>
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
<h3 style="text-align: center">文件显示</h3>
<div class="panel-body" style="padding-bottom:0px;">
    <div class="panel panel-default">
        <div class="panel-heading">查询条件</div>
        <div class="panel-body">
            <form id="formSearch" class="form-horizontal">
                <div class="form-group" style="margin-top:15px">
                    <label class="control-label col-sm-1" for="search_fileName"></label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="search_fileName" placeholder="请输入文件名">
                    </div>
                    <div class="col-sm-4" style="text-align:left;">
                        <button type="button" style="margin-left:10px"
                                class="btn btn-primary" onclick="searchFile()">查询</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<table id="mytable"></table>
</body>
<script>
    function searchFile(){
        $("#mytable").bootstrapTable('refresh' , {
            query : {
                offset: '0',
                limit: '5',
                'search_name' : $("#search_fileName").val()
            }
        })
    }

    function down(id){
        /*$.ajax({
            url: "FileServlet",
            data: {
                "op": "doDownLoad",
                "id": id
            },
            type: "get",
            success: function (msg) {
                if (msg == "ok") {
                    $("#mytable").bootstrapTable("refresh");//刷新表格
                    alert("下载成功！");
                } else if (msg == "no") {
                    alert("下载失败！");
                }
            }
        });*/
        location.href = "FileServlet?op=doDownLoad&id="+id;
    }

    function del(id) {
        $.ajax({
            url: "FileServlet",
            data: {
                "op": "doDeleteFile",
                "id": id
            },
            type: "get",
            success: function (msg) {
                if (msg == "ok") {
                    $("#mytable").bootstrapTable("refresh");//刷新表格
                    alert("删除成功！");
                } else if (msg == "no") {
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
                url: 'FileServlet',         //请求后台的URL（*）
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
                    field: 'id',
                    title: '文件ID'
                }, {
                    field: 'fileName',
                    title: '文件名称'
                }, {
                    field: 'autoName',
                    title: '文件别名'
                }, {
                    field: 'fileDesc',
                    title: '文件描述'
                }, {
                    field: 'userName',
                    title: '文件上传者'
                }, {
                    field: 'id',
                    title: '操作',
                    formatter: downAndDel
                }]
            });
        }
        //加载表格的参数
        obj.queryParams = function (params) {
            var temp = {
                limit : params.limit,
                offset : params.offset,
                'op' : 'doShowAllFile',
                'search_name' : $("#search_fileName").val()
            }
            return temp;
        }

        return obj;
    }

    function downAndDel(id, row, index) {
        return `
            <a href="#" class="glyphicon glyphicon-download-alt" onclick="down(` + id + `)"></a>
            &nbsp; &nbsp; &nbsp; &nbsp;
            <a href="#" class="glyphicon glyphicon-trash" onclick="del(` + id + `)"></a>
       `;
    }
</script>
</html>
