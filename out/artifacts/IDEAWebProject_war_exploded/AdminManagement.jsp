<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="LoginQuery.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>电子商务管理系统</title>
    <link rel="stylesheet" type="text/css" href="JavaScript/bootstrap-3.4.1/dist/css/bootstrap.css"/>
    <script src="JavaScript/jquery-1.12.4.js"></script>
    <script src="JavaScript/bootstrap-3.4.1/dist/js/bootstrap.min.js"></script>
</head>
<style type="text/css">
    .top {
        padding-left: 15px;
    }

    a:hover {
        text-decoration: none;
    }

    .down {
        height: 600px;
    }

    .left {
        height: 600px;
    }

    .right {
        height: 600px;
    }

    iframe {
        width: 100%;
        height: 100%;
    }
</style>
<body>
<div class="container">
    <div class="top">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Brand</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true" aria-expanded="false">
                                Dropdown
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="LoginOut.jsp">退出登录</a></li>
                            </ul>
                        </li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
    </div>

    <div class="down">
        <div class="left col-xs-2">
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                               aria-expanded="true" aria-controls="collapseOne">
                                产品管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <a class="btn btn-primary btn-block" href="/AdminManage/ShowProducts/ShowAllProduct.jsp"
                               target="ShowAll">显示产品</a>
                            <a class="btn btn-primary btn-block" target="ShowAll">新增产品</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                目录管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <a class="btn btn-primary btn-block" href="/AdminManage/ShowCategories/ShowAllCategory.jsp"
                               target="ShowAll">显示目录</a>
                            <a class="btn btn-primary btn-block" data-toggle="modal"
                               data-target="#myModal" target="ShowAll">新增目录</a>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree" aria-expanded="false" aria-controls="collapseFour">
                                供应商管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingThree">
                        <div class="panel-body">
                            <a class="btn btn-primary btn-block" href="/AdminManage/ShowProviders/ShowAllProvider.jsp"
                               target="ShowAll">显示供应商</a>
                            <a class="btn btn-primary btn-block" target="ShowAll">新增供应商</a>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFour">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                订单管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingFour">
                        <div class="panel-body">
                            <a class="btn btn-primary btn-block">显示订单</a>
                            <a class="btn btn-primary btn-block" href="AdminManage/ShowOrders/OrderServlet?op=doAddOrder" target="ShowAll">新增订单</a>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFive">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                Cookie测试
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingFive">
                        <div class="panel-body">
                            <a class="btn btn-primary btn-block" href="Cookie/showGoods.jsp" target="ShowAll">显示商品</a>
                            <a class="btn btn-primary btn-block" href="Cookie/showCart.jsp" target="ShowAll">显示购物车</a>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingSix">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
                                文件管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseSix" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingSix">
                        <div class="panel-body">
                            <a class="btn btn-primary btn-block" href="MyFile/ShowAllFile.jsp" target="ShowAll">显示文件</a>
                            <a class="btn btn-primary btn-block" href="MyFile/upLoad.jsp" target="ShowAll">文件上传</a>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <div class="right col-xs-10">
            <iframe name="ShowAll" src="WelcomeAdmin.jsp" frameborder="0"></iframe>
        </div>
    </div>
</div>

<!-- Modal(新增目录的模态框) -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新增界面</h4>
            </div>
            <div class="modal-body">
                <form class="form form-horizontal">
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
                <button type="button" class="btn btn-primary" onclick="doAdd()">Save changes</button>
            </div>
        </div>
    </div>
</div>

</body>
<script>
    function doAdd() {
        $.ajax({
            url: "/AdminManage/ShowCategories/CategoryServlet",
            data: {
                "op": "doIncrease",
                "category_name": $("#category_name").val(),
                "category_desc": $("#category_desc").val()
            },
            type: "get",
            success: function (msg) {
                if (msg == "1") {
                    $("#myModal").modal('hide');//修改成功后关闭模态框
                    $("iframe").attr('src','/AdminManage/ShowCategories/ShowAllCategory.jsp');
                    alert("添加成功！");
                } else if (msg == "0") {
                    alert("添加失败！");
                } else {
                    alert("该目录不可重复添加！");
                }
            }
        });
    }
</script>
</html>

